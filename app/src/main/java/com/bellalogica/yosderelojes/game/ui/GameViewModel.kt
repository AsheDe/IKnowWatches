package com.bellalogica.yosderelojes.game.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bellalogica.yosderelojes.core.domain.repository.IKnowWatchesRepository
import com.bellalogica.yosderelojes.game.model.Answers
import com.bellalogica.yosderelojes.game.model.Question
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class GameViewModel(private val repository: IKnowWatchesRepository) : ViewModel() {
    var gameState: MutableState<GameState> =
        mutableStateOf(GameState(listOfLevelQuestions = listOf(), userState = UserState()))
        private set

    fun getGameState() {
        viewModelScope.launch {
            var listQuestions = mutableListOf<Question>()
            repository.getLevel(1).onSuccess { listOfQuestions ->
                gameState.value = GameState(
                    listOfLevelQuestions = listOfQuestions, userState = UserState()
                )
            }
            var getUser = UserState()
            repository.getUserInfo().onSuccess {
                if (it != null) {
                    getUser = it
                }
            }
            gameState.value = GameState(
                listOfLevelQuestions = listQuestions, userState = getUser
            )
        }
    }

    private val _uiEvent = Channel<GameEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val timer = 0


    fun onGameEvent(event: UserGameEvents) {
        when (event) {
            is UserGameEvents.OnNavigateBack -> {
                _uiEvent.trySend(GameEvents.OnExitGameScreen)
            }

            is UserGameEvents.OnOpenScores -> {
                _uiEvent.trySend(GameEvents.OnOpenScoreScreen)
            }

            is UserGameEvents.OnAnswerSelected -> {
                processAnswer(event.answer)
            }

            UserGameEvents.OnNavigateBack -> {
                _uiEvent.trySend(GameEvents.OnOpenScoreScreen)
            }

            UserGameEvents.OnOpenScores -> {
                _uiEvent.trySend(GameEvents.OnOpenScoreScreen)
            }
        }
    }

    private fun processAnswer(answer: Answers) {
        if (answer.isCorrectAnswer) {
            viewModelScope.launch {
                gameState.value.userState.score = gameState.value.userState.score + timer * 10
            }
        } else {
            viewModelScope.launch {
                gameState.value.userState.score = gameState.value.userState.score - 50
                gameState.value.userState.lives -= 1
            }
        }/*viewModelScope.launch {
            _gameState.update {
                if (it.listOfLevelQuestions.size == 1) {
                    database.hasMoreLevels().let { hasMoreLevels ->
                        if (hasMoreLevels) {
                            it.copy(
                                listOfLevelQuestions = database.getGameLevel().shuffled(),
                            )
                        } else {
                            _uiEvent.trySend(GameEvents.OnGameEnded)
                            it
                        }
                    }
                    _uiEvent.trySend(GameEvents.OnLevelEnded)
                    it
                } else {
                    it.copy(
                        listOfLevelQuestions = it.listOfLevelQuestions.toMutableList().drop(1),
                    )
                }
            }
        }*/
    }
}
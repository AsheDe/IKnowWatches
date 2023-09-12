package com.bellalogica.yosderelojes.game.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bellalogica.yosderelojes.game.model.Answers
import com.bellalogica.yosderelojes.game.model.local.DataBase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel(private val database: DataBase) : ViewModel() {


    private val _gameState = MutableStateFlow(
        GameState(
            database.getGameLevel().shuffled(), database.getUserInfo() ?: UserState()
        )
    )
    val gameState = _gameState

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
                _gameState.update {
                    it.copy(
                        userState = it.userState.copy(
                            score = it.userState.score + (timer * 10)
                        )
                    )
                }
            }
        } else {
            viewModelScope.launch {
                _gameState.update {
                    it.copy(
                        userState = it.userState.copy(
                            score = it.userState.score - 50
                        )
                    )
                }
            }
        }
        viewModelScope.launch {
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
        }
    }
}
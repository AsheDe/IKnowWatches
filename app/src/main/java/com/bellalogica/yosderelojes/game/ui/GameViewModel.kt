package com.bellalogica.yosderelojes.game.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bellalogica.yosderelojes.core.domain.repository.IKnowWatchesRepository
import com.bellalogica.yosderelojes.game.model.Answers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class GameViewModel(private val repository: IKnowWatchesRepository) : ViewModel() {
    var gameState = mutableStateOf(GameState(listOfLevelQuestions = emptyList(), userState = UserState()))
        private set

    fun getGameLevelToStart() {
        viewModelScope.launch (Dispatchers.IO){
            val level  =  repository.getCurrentPlayingLevel()
            val levelQuestions = repository.getQuestionsForLevel(level)
            val userInfo = repository.getUserInfo()
            gameState.value = GameState(level, levelQuestions, userInfo)
        }
    }

    /*fun getGameState() {
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
    }*/

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
                //update user info in db
                gameState.value = gameState.value.copy(
                    currentQuestion = if(gameState.value.currentQuestion < gameState.value.listOfLevelQuestions.size - 1) {
                        gameState.value.currentQuestion + 1
                    } else {
                        0
                    }
                )
            }
        }
    }

    private fun processAnswer(answer: Answers) {
        if (answer.isCorrectAnswer) {
            viewModelScope.launch {
                gameState.value =  gameState.value.copy(
                    userState = gameState.value.userState.copy(
                        score = gameState.value.userState.score + 60
                    )
                )
            }
        } else {
            viewModelScope.launch {
                gameState.value =  gameState.value.copy(
                    userState = gameState.value.userState.copy(
                        score = gameState.value.userState.score - 45,
                        lives = gameState.value.userState.lives - 1
                    )
                )
            }
        }
    }
}
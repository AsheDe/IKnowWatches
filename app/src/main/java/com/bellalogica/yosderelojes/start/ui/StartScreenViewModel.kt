package com.bellalogica.yosderelojes.start.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bellalogica.yosderelojes.R
import com.bellalogica.yosderelojes.core.model.GameStatus
import com.bellalogica.yosderelojes.utils.UiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class StartScreenViewModel: ViewModel() {

    var startState = mutableStateOf(StartScreenState(StartScreenInfo.Loading))
        private set

    private val _uiEvent = Channel<StartScreenEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _gameStatus = MutableStateFlow<GameStatus?>(null)

    fun onEnterGameClicked(event: StartScreenEvents) {
        when (event) {
            is StartScreenEvents.EnterGameClicked -> {
                    when(startState.value.startScreenInfo) {
                        StartScreenInfo.Loading -> {
                            viewModelScope.launch {
                                _uiEvent.send(StartScreenEvents.ShowMessage(UiText.StringResource(R.string.start_screen_still_loading)))
                            }
                        }
                        StartScreenInfo.Error -> {
                            viewModelScope.launch {
                                _uiEvent.send(StartScreenEvents.ShowMessage(UiText.StringResource(R.string.start_screen_info_error)))
                            }
                        }
                        StartScreenInfo.Success -> {
                            _gameStatus.value?.let {
                                viewModelScope.launch {
                                    _uiEvent.send(StartScreenEvents.GoToGame(it)) ///  dependera del estado de la info guardada
                                }
                            }
                        }
                    }
                }

            else -> {}
        }
    }

    fun getGameStatus() {
        viewModelScope.launch {
            // get game status
            delay(5000L)
            startState.value = StartScreenState(StartScreenInfo.Success)
            _gameStatus.value = GameStatus()
        }
    }
}
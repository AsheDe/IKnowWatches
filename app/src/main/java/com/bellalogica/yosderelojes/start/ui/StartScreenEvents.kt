package com.bellalogica.yosderelojes.start.ui

import com.bellalogica.yosderelojes.core.model.GameStatus
import com.bellalogica.yosderelojes.utils.UiText

sealed class StartScreenEvents {
    object EnterGameClicked : StartScreenEvents()
    data class GoToGame(val gameStatus: GameStatus) : StartScreenEvents()
    data class ShowMessage(val message: UiText.StringResource) : StartScreenEvents()

}
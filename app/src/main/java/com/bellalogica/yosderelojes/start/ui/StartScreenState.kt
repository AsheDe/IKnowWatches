package com.bellalogica.yosderelojes.start.ui

data class StartScreenState(val startScreenInfo: StartScreenInfo)

sealed class StartScreenInfo {
    object Loading: StartScreenInfo()
    object Error: StartScreenInfo()
    object Success: StartScreenInfo()
}
package com.bellalogica.yosderelojes.game.ui

import com.bellalogica.yosderelojes.game.model.Answers
import com.bellalogica.yosderelojes.game.model.Question

sealed class UserGameEvents {
    data class OnAnswerSelected(val answer: Answers) : UserGameEvents()

    object OnNavigateBack: UserGameEvents()

    object OnOpenScores: UserGameEvents()
}

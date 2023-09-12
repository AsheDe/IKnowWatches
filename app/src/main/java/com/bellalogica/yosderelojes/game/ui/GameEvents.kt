package com.bellalogica.yosderelojes.game.ui

import com.bellalogica.yosderelojes.game.model.Question

sealed class GameEvents {
    data class GameLevelLoaded(val listOfLevelQuestions: List<Question>) : GameEvents()

    data class OnQuestionTimeExpired(val question: Question) : GameEvents()
    data class OnQuestionAnswered(val question: Question) : GameEvents()

    object OnLevelEnded: GameEvents()

    object OnGameEnded: GameEvents()

    object OnExitGameScreen: GameEvents()

    object OnOpenScoreScreen: GameEvents()
}

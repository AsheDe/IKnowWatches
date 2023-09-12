package com.bellalogica.yosderelojes.game.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bellalogica.yosderelojes.game.model.Question

@Composable
fun GameContent(gameState: GameState, onGameEvent: (UserGameEvents) -> Unit, modifier: Modifier) {
    when( gameState.listOfLevelQuestions.first()) {
        is Question.FourPicturesQuestion -> {
            FourPicturesQuestion(
                question = gameState.listOfLevelQuestions.first() as Question.FourPicturesQuestion,
                event = onGameEvent
            )
        }
        is Question.FourTextsQuestion -> {
            FourStringsQuestion(
                question = gameState.listOfLevelQuestions.first() as Question.FourTextsQuestion,
                event = onGameEvent
            )
        }
    }
}

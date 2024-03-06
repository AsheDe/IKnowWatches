package com.bellalogica.yosderelojes.game.ui

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bellalogica.yosderelojes.game.model.Question
import com.bellalogica.yosderelojes.ui.theme.Yellow100
import com.bellalogica.yosderelojes.ui.theme.Yellow40

@Composable
fun GameContent(gameState: GameState, onGameEvent: (UserGameEvents) -> Unit, modifier: Modifier) {
    val context = LocalContext.current
    if(gameState.listOfLevelQuestions.isNotEmpty()) {
        when (gameState.listOfLevelQuestions[gameState.currentQuestion]) {
            is Question.FourPicturesQuestion -> {
                FourPicturesQuestion(
                    question = gameState.listOfLevelQuestions[gameState.currentQuestion] as Question.FourPicturesQuestion,
                    event = onGameEvent
                )
            }

            is Question.FourTextsQuestion -> {
                FourStringsQuestion(
                    question = gameState.listOfLevelQuestions[gameState.currentQuestion] as Question.FourTextsQuestion,
                    event = onGameEvent
                )
            }
        }
    } else {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize()
            .padding(72.dp)
            .padding(top = 96.dp),
            strokeWidth = 8.dp,
            color = Yellow100,
            trackColor = Yellow40
        )
    }
}

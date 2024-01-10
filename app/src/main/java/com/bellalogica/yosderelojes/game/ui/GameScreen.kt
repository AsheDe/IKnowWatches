package com.bellalogica.yosderelojes.game.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bellalogica.yosderelojes.R
import com.bellalogica.yosderelojes.game.model.Answers
import com.bellalogica.yosderelojes.game.model.ImageWrapper
import com.bellalogica.yosderelojes.game.model.Question

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    gameState: GameState,
    onGameEvent: (UserGameEvents) -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween
    ) {
        GameTopBar(
            gameState = gameState,
            modifier = Modifier
                .height(72.dp)
        )

        GameContent(
            gameState = gameState,
            onGameEvent = onGameEvent,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
        )
    }
}

@Preview(showBackground = true, device = "spec:parent=Nexus One", apiLevel = 28)
@Composable
fun showGamePreview() {
    GameScreen(gameState = GameState(
        listOfLevelQuestions = listOf(
            Question.FourPicturesQuestion(
                questionText = "¿Cuál es el nombre de este reloj?", answers = listOf(
                    Answers.ImageAnswer(ImageWrapper.ResourcesImage(R.mipmap.vacheron), true),
                    Answers.ImageAnswer(ImageWrapper.ResourcesImage(R.mipmap.vacheron), false),
                    Answers.ImageAnswer(ImageWrapper.ResourcesImage(R.mipmap.vacheron), false),
                    Answers.ImageAnswer(ImageWrapper.ResourcesImage(R.mipmap.vacheron), false)
                )
            ), Question.FourTextsQuestion(
                questionText = "¿Cuál es el nombre de este reloj?",
                leadingImage = ImageWrapper.ResourcesImage(R.mipmap.vacheron),
                answers = listOf(
                    Answers.TextAnswer("answ 1", true),
                    Answers.TextAnswer("answ 2", false),
                    Answers.TextAnswer("answ 3", false),
                    Answers.TextAnswer("answ 4", false)
                )
            )
        ), userState = UserState()
    ), onGameEvent = { })
}


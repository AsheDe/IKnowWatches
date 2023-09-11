package com.bellalogica.yosderelojes.game.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.bellalogica.yosderelojes.R
import com.bellalogica.yosderelojes.core.ui.ButtonInFourTextsQuestion
import com.bellalogica.yosderelojes.game.model.Answers
import com.bellalogica.yosderelojes.game.model.ImageWrapper
import com.bellalogica.yosderelojes.game.model.Question

@Composable
fun FourStringsQuestion(
    question: Question.FourTextsQuestion,
    event: (Question) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 32.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Image(painter = rememberAsyncImagePainter((question.leadingImage.resource)),
                contentDescription = "leading_image_desc",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(8.dp)
                    .border(4.dp, MaterialTheme.colorScheme.primary)
                    .fillMaxHeight(0.35f)
                    .clickable { })

            Text(
                text = question.questionText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Column(modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly
                ) {
                ButtonInFourTextsQuestion(
                    modifier = Modifier,
                    text = question.answers[0].content,
                    event = { event(question) }
                )

                ButtonInFourTextsQuestion(
                    modifier = Modifier,
                    text = question.answers[1].content,
                    event = { event(question) }
                )

                ButtonInFourTextsQuestion(
                    modifier = Modifier,
                    text = question.answers[2].content,
                    event = { event(question) }
                )

                ButtonInFourTextsQuestion(
                    modifier = Modifier,
                    text = question.answers[3].content,
                    event = { event(question) }
                )
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:parent=Nexus One", apiLevel = 28)
@Composable
fun showPreview() {
    FourStringsQuestion(
        question = Question.FourTextsQuestion(
            questionText = "¿Cuál es el nombre de este reloj?",
            leadingImage = ImageWrapper.ResourcesImage(R.mipmap.vacheron),
            answers = listOf(
                Answers.TextAnswer("answ 1", true),
                Answers.TextAnswer("answ 2", false),
                Answers.TextAnswer("answ 3", false),
                Answers.TextAnswer("answ 4", false)
            )
        ),
        event = { }
    )
}
package com.bellalogica.yosderelojes.game.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bellalogica.yosderelojes.R
import com.bellalogica.yosderelojes.core.ui.ImageInFourPicturesQuestion
import com.bellalogica.yosderelojes.game.model.Answers
import com.bellalogica.yosderelojes.game.model.ImageWrapper
import com.bellalogica.yosderelojes.game.model.Question
import com.bellalogica.yosderelojes.ui.theme.MyFontFamily

@Composable
fun FourPicturesQuestion(
    question: Question.FourPicturesQuestion,
    event: (UserGameEvents) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
    ) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
        ) {
            Row(modifier = Modifier.fillMaxWidth().weight(1f)) {

                ImageInFourPicturesQuestion(
                    modifier = Modifier
                        .weight(1f),
                    image = question.answers[0].content,
                    event = {
                        event(UserGameEvents.OnAnswerSelected(question.answers[0]))
                            },
                    description = "image_0"
                )

                ImageInFourPicturesQuestion(
                    modifier = Modifier
                        .weight(1f),
                    image = question.answers[1].content,
                    event = { event(UserGameEvents.OnAnswerSelected(question.answers[1])) },
                    description = "image_1"
                )
            }

            Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                ImageInFourPicturesQuestion(
                    modifier = Modifier
                        .weight(1f),
                    image = question.answers[2].content,
                    event = { event(UserGameEvents.OnAnswerSelected(question.answers[2])) },
                    description = "image_2"
                )

                ImageInFourPicturesQuestion(
                    modifier = Modifier
                        .weight(1f),
                    image = question.answers[3].content,
                    event = { event(UserGameEvents.OnAnswerSelected(question.answers[3])) },
                    description = "image_3"
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))

        Box(modifier = Modifier.weight(0.5f).fillMaxWidth()) {
            Text(
                text = question.questionText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
               // style = TextStyle(fontSize = 36.sp, color = MaterialTheme.colorScheme.primary),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                fontFamily = MyFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
            )
        }
    }
}

@Preview(showBackground = true, device = "spec:parent=Nexus One", apiLevel = 28)
@Composable
fun PreviewFourPicturesScreen(){
    FourPicturesQuestion(question = Question.FourPicturesQuestion("question",
        listOf(
            Answers.ImageAnswer(ImageWrapper.ResourcesImage(R.mipmap.vacheron)),
            Answers.ImageAnswer(ImageWrapper.ResourcesImage(R.mipmap.vacheron)),
            Answers.ImageAnswer(ImageWrapper.ResourcesImage(R.mipmap.vacheron)),
            Answers.ImageAnswer(ImageWrapper.ResourcesImage(R.mipmap.vacheron)),
        )),
        event = {})
}
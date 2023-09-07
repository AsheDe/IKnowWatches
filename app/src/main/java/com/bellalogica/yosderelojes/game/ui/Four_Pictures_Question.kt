package com.bellalogica.yosderelojes.game.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.bellalogica.yosderelojes.R
import com.bellalogica.yosderelojes.core.ui.ImageInFourPicturesQuestion
import com.bellalogica.yosderelojes.game.model.Answers
import com.bellalogica.yosderelojes.game.model.ImageWrapper
import com.bellalogica.yosderelojes.game.model.Question

@Composable
fun Four_Pictures_Question(
    question: Question.FourPicturesQuestion,
    event: (Question) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .padding(top = 64.dp)
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
                    event = { event(question) }
                )

                ImageInFourPicturesQuestion(
                    modifier = Modifier
                        .weight(1f),
                    image = question.answers[1].content,
                    event = { event(question) }
                )
            }

            Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                ImageInFourPicturesQuestion(
                    modifier = Modifier
                        .weight(1f),
                    image = question.answers[2].content,
                    event = { event(question) }
                )

                ImageInFourPicturesQuestion(
                    modifier = Modifier
                        .weight(1f),
                    image = question.answers[3].content,
                    event = { event(question) }
                )
            }
        }
        Spacer(modifier = Modifier.height(64.dp))

        Box(modifier = Modifier.weight(0.5f).fillMaxWidth()) {
            Text(
                text = question.questionText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
               // style = TextStyle(fontSize = 36.sp, color = MaterialTheme.colorScheme.primary),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 40.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFourPicturesScreen(){
    Four_Pictures_Question(question = Question.FourPicturesQuestion("question",
        listOf(
            Answers.ImageAnswer(ImageWrapper.ResourcesImage(R.mipmap.vacheron)),
            Answers.ImageAnswer(ImageWrapper.ResourcesImage(R.mipmap.vacheron)),
            Answers.ImageAnswer(ImageWrapper.ResourcesImage(R.mipmap.vacheron)),
            Answers.ImageAnswer(ImageWrapper.ResourcesImage(R.mipmap.vacheron)),
        )),
        event = {})
}
package com.bellalogica.yosderelojes.game.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bellalogica.yosderelojes.game.model.Answers
import com.bellalogica.yosderelojes.game.model.ImageWrapper
import com.bellalogica.yosderelojes.game.model.Question
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FourStringsQuestionTest {
    @get:Rule
    val composeRule = createComposeRule()
    @Test
    fun isScreenDisplayedProperly() {
        composeRule.setContent {
            FourStringsQuestion(
                question = Question.FourTextsQuestion(
                    leadingImage = ImageWrapper.NetworkImage(TEST_IMGE_URL),
                    questionText = QUESTION_STRING,
                    answers = listOf(
                        Answers.TextAnswer(CONTENT_DESC_0, false),
                        Answers.TextAnswer(CONTENT_DESC_1, true),
                        Answers.TextAnswer(CONTENT_DESC_2, false),
                        Answers.TextAnswer(CONTENT_DESC_3, false),
                    )),
                event = {

                })
        }
        composeRule.onNodeWithText(QUESTION_STRING).assertIsDisplayed()
        composeRule.onNodeWithText(CONTENT_DESC_0).assertIsDisplayed()
        composeRule.onNodeWithText(CONTENT_DESC_1).assertIsDisplayed()
        composeRule.onNodeWithText(CONTENT_DESC_2).assertIsDisplayed()
        composeRule.onNodeWithText(CONTENT_DESC_3).assertIsDisplayed()
        composeRule.onNodeWithContentDescription(LEADING_IMAGE_DESC).assertIsDisplayed()

    }

    companion object {
        private const val TAG = "FourPicturesScreen"
        private const val QUESTION_STRING = "¿Cuánto sabes de relojes?"
        private const val CONTENT_DESC_0 = "text_0"
        private const val CONTENT_DESC_1 = "text_1"
        private const val CONTENT_DESC_2 = "text_2"
        private const val CONTENT_DESC_3 = "text_3"
        private const val LEADING_IMAGE_DESC = "leading_image_desc"

        private const val TEST_IMGE_URL = "https://github.com/AsheDe/IKnowWatches/blob/main/app/src/main/res/mipmap-hdpi/vacheron.png"
    }
}
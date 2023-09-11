package com.bellalogica.yosderelojes.game.ui

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.core.app.ActivityCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.bellalogica.yosderelojes.game.model.Answers
import com.bellalogica.yosderelojes.game.model.ImageWrapper
import com.bellalogica.yosderelojes.game.model.Question
import com.bellalogica.yosderelojes.start.ui.StartScreenInfo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FourPicturesQuestionTest {

    @get:Rule
    val composeRule = createComposeRule()
    @Test
    fun isScreenDisplayedProperly() {
        composeRule.setContent {
            FourPicturesQuestion(
                question = Question.FourPicturesQuestion(QUESTION_STRING,
                    listOf(
                    Answers.ImageAnswer(ImageWrapper.NetworkImage(TEST_IMGE_URL), false),
                    Answers.ImageAnswer(ImageWrapper.NetworkImage(TEST_IMGE_URL), true),
                    Answers.ImageAnswer(ImageWrapper.NetworkImage(TEST_IMGE_URL), false),
                    Answers.ImageAnswer(ImageWrapper.NetworkImage(TEST_IMGE_URL), false),
                )),
                event = {

            })
        }
        composeRule.onNodeWithText(QUESTION_STRING).assertIsDisplayed()
        composeRule.onNodeWithContentDescription(CONTENT_DESC_0).assertIsDisplayed()
        composeRule.onNodeWithContentDescription(CONTENT_DESC_1).assertIsDisplayed()
        composeRule.onNodeWithContentDescription(CONTENT_DESC_2).assertIsDisplayed()
        composeRule.onNodeWithContentDescription(CONTENT_DESC_3).assertIsDisplayed()

    }

    companion object {
        private const val TAG = "FourPicturesScreen"
        private const val QUESTION_STRING = "¿Cuánto sabes de relojes?"
        private const val CONTENT_DESC_0 = "image_0"
        private const val CONTENT_DESC_1 = "image_1"
        private const val CONTENT_DESC_2 = "image_2"
        private const val CONTENT_DESC_3 = "image_3"

        private const val TEST_IMGE_URL = "https://github.com/AsheDe/IKnowWatches/blob/main/app/src/main/res/mipmap-hdpi/vacheron.png"
    }
}
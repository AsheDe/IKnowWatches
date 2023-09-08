package com.bellalogica.yosderelojes.start.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StartScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun start_Screen() {
        composeRule.setContent {
            StartScreen(startScreenState = StartScreenInfo.Loading, onEvent = {})
        }
        composeRule.onNodeWithText(TITLE_STRING).assertIsDisplayed()
        composeRule.onNodeWithTag(BUTTON_TAG).assertIsDisplayed()
        composeRule.onNodeWithTag(BUTTON_STATE_LOADING).assertIsDisplayed()
    }

    @Test
    fun show_Error() {
        composeRule.setContent {
            StartScreen(startScreenState = StartScreenInfo.Error, onEvent = {})
        }
        composeRule.onNodeWithTag(BUTTON_STATE_ERROR, true).assertIsDisplayed()
    }

    @Test
    fun show_Success() {
        composeRule.setContent {
            StartScreen(startScreenState = StartScreenInfo.Success, onEvent = {})
        }
        composeRule.onNodeWithTag(BUTTON_STATE_SUCCESS, true).assertIsDisplayed()
        composeRule.onNodeWithTag(BUTTON_STATE_SUCCESS, true).performClick()

    }

    companion object {
        private const val TAG = "StartScreenTest"
        private const val TITLE_STRING = "¿Cuánto sabes de relojes?"
        private const val BUTTON_TAG = "start_button"
        private const val BUTTON_STATE_LOADING = "info_loading"
        private const val BUTTON_STATE_ERROR = "info_error"
        private const val BUTTON_STATE_SUCCESS = "info_success"
    }
}
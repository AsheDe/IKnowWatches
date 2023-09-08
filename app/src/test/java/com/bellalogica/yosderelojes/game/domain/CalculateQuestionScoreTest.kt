package com.bellalogica.yosderelojes.game.domain

import org.junit.Assert.*

import org.junit.Test

class CalculateQuestionScoreTest {

    @Test
    fun testInvokeUseCase() {
        val calculateQuestionScore = CalculateQuestionScore()
        val result = calculateQuestionScore(remainingTime = 10, questionScore = 10)
        assertEquals(100, result)
    }
}
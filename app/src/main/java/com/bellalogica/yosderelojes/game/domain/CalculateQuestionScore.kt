package com.bellalogica.yosderelojes.game.domain

class CalculateQuestionScore {
    operator fun invoke(remainingTime: Int, questionScore: Int): Int {
        return remainingTime * questionScore
    }
}
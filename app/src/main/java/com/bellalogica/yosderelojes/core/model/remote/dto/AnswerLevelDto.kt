package com.bellalogica.yosderelojes.core.model.remote.dto

import com.bellalogica.yosderelojes.game.model.Answers
import com.bellalogica.yosderelojes.game.model.ImageWrapper

data class AnswerLevelDto(
    val content: String, val isCorrectAnswer: Boolean
) {
    fun toTextAnswer(): Answers.TextAnswer {
        return Answers.TextAnswer(content, isCorrectAnswer)
    }

    fun toImageAnswer(): Answers.ImageAnswer {
        return Answers.ImageAnswer(ImageWrapper.NetworkImage(content), isCorrectAnswer)
    }
}
package com.bellalogica.yosderelojes.core.model.remote.dto

import com.bellalogica.yosderelojes.game.model.Answers
import com.bellalogica.yosderelojes.game.model.Question
import com.bellalogica.yosderelojes.game.model.ImageWrapper

typealias ImageUrl = String
sealed class QuestionDto(open val type: String) {
    data class FourTextsQuestion(
        val answers: List<String>,
        val leadingImage: ImageUrl,
        val questionText: String,
        override val type: String,
    ) : QuestionDto(type = type){

    }

    data class FourPicturesQuestion(
        val answers: List<ImageUrl>,
        val questionText: String,
        override val type: String,
        ): QuestionDto(type = type)

    open fun toQuestion(): Question {
        return when (this) {
            is FourTextsQuestion -> Question.FourTextsQuestion(
                answers = answers.map { Answers.TextAnswer(it) },
                leadingImage = ImageWrapper.NetworkImage(resource = leadingImage),
                questionText = questionText,
            )
            is FourPicturesQuestion -> Question.FourPicturesQuestion(
                answers = answers.map { Answers.ImageAnswer(
                    content = ImageWrapper.NetworkImage(resource = it)
                ) },
                questionText = questionText,
            )
        }
    }
}
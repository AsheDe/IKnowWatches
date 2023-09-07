package com.bellalogica.yosderelojes.game.model

sealed class Answers(open val  isCorrectAnswer: Boolean = false) {
    data class StringAnswer(val content: String, override val  isCorrectAnswer: Boolean= false): Answers(isCorrectAnswer)

    data class ImageAnswer(val content: ImageWrapper, override val  isCorrectAnswer: Boolean= false): Answers(isCorrectAnswer)
}

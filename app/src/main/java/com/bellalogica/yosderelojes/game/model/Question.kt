package com.bellalogica.yosderelojes.game.model

sealed class Question {
    data class FourPicturesQuestion( val questionText: String, val answers: List<Answers.ImageAnswer>): Question()
    data class FourTextsQuestion(val questionText: String, val answers: List<Answers.TextAnswer>, val leadingImage: ImageWrapper): Question()
}

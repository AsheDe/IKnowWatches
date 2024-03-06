package com.bellalogica.yosderelojes.core.model.remote.dto

data class QuestionLevelDto(
    val answers: List<AnswerLevelDto>,
    val leadingImage: String,
    val questionText: String,
    val questionType: String,
    val level: Int
)
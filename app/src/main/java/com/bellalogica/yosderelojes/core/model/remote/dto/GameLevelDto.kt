package com.bellalogica.yosderelojes.core.model.remote.dto

import com.bellalogica.yosderelojes.game.model.ImageWrapper
import com.bellalogica.yosderelojes.game.model.Question

class GameLevelDto : ArrayList<QuestionLevelDto>() {
    fun toListOfQuestions(): List<Question> {
        return map {
            when (it.questionType) {
                QuestionType.FourPictures.name -> {
                    Question.FourPicturesQuestion(
                        questionText = it.questionText,
                        answers = it.answers.map { answer ->
                            answer.toImageAnswer()
                        },
                    )
                }

                else -> {
                    Question.FourTextsQuestion(
                        questionText = it.questionText,
                        answers = it.answers.map { answer ->
                            answer.toTextAnswer()
                        },
                        leadingImage = ImageWrapper.NetworkImage(it.leadingImage)
                    )
                }

            }
        }
    }
}

    enum class QuestionType {
        FourPictures, FourTexts
    }
package com.bellalogica.yosderelojes.core.model.remote.dto

import com.bellalogica.yosderelojes.core.model.local.entity.AnswersEntity
import com.bellalogica.yosderelojes.core.model.local.entity.QuestionsEntity
import com.bellalogica.yosderelojes.game.model.ImageWrapper
import com.bellalogica.yosderelojes.game.model.Question

class GameLevelDto : ArrayList<QuestionLevelDto>() {

    fun toListOfQuestionsEntity(): List<QuestionsEntity> {
        return mapIndexed { index, questionLevelDto ->
            QuestionsEntity(
                id = index,
                questionText = questionLevelDto.questionText,
                questionType = questionLevelDto.questionType,
                leadingImage = questionLevelDto.leadingImage,
                level = questionLevelDto.level
            )
        }
    }

    fun toListOfAnswersEntity(): List<AnswersEntity> {
        val answers = mutableListOf<AnswersEntity>()
        forEachIndexed {  question, questionLevelDto ->
            questionLevelDto.answers.forEach { answerLevelDto ->
                answers.add(AnswersEntity(
                    id = answers.size,
                    questionId = question,
                    content = answerLevelDto.content,
                    isCorrectAnswer = answerLevelDto.isCorrectAnswer,
                    level = questionLevelDto.level))
            }
        }
        return answers
    }

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
                        questionText = it.questionText, answers = it.answers.map { answer ->
                            answer.toTextAnswer()
                        }, leadingImage = ImageWrapper.NetworkImage(it.leadingImage)
                    )
                }

            }
        }
    }
}

enum class QuestionType {
    FourPictures, FourTexts
}
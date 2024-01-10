package com.bellalogica.yosderelojes.core.model.remote

import com.bellalogica.yosderelojes.game.model.Answers
import com.bellalogica.yosderelojes.game.model.ImageWrapper
import com.bellalogica.yosderelojes.game.model.Question
import org.json.JSONArray

fun JSONArray.toListOfQuestions(): List<Question> {
    val list = mutableListOf<Question>()
    for (i in 0 until length()) {
        if (getJSONObject(i).getString("questionType") == "fourPictures") {
            list.add(
                Question.FourPicturesQuestion(
                    answers = getJSONObject(i).getJSONArray("answers").toListOfPictureAnswers(),
                    questionText = getJSONObject(i).getString("questionText"),
                )
            )
        }
        if (getJSONObject(i).getString("questionType") == "fourTexts") {
            list.add(
                Question.FourTextsQuestion(
                    answers = getJSONObject(i).getJSONArray("answers").toListOfTextAnswers(),
                    questionText = getJSONObject(i).getString("questionText"),
                    leadingImage = ImageWrapper.NetworkImage(getJSONObject(i).getString("leadingImage"))
                )
            )
        }
    }
    return list
}

fun JSONArray.toListOfPictureAnswers(): List<Answers.ImageAnswer> {
    val list = mutableListOf<Answers.ImageAnswer>()
    for (i in 0 until  length()) {
        list.add(
            Answers.ImageAnswer(
                ImageWrapper.NetworkImage(getJSONObject(i).getString("content")),
                getJSONObject(i).getBoolean("isCorrectAnswer")
            ))
    }
    return list
}

fun JSONArray.toListOfTextAnswers(): List<Answers.TextAnswer> {
    val list = mutableListOf<Answers.TextAnswer>()
    for (i in 0 until  length()) {
        list.add(
            Answers.TextAnswer(
                getJSONObject(i).getString("content"),
                getJSONObject(i).getBoolean("isCorrectAnswer")
            ))
    }
    return list
}
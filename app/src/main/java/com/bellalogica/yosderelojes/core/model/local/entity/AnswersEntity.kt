package com.bellalogica.yosderelojes.core.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bellalogica.yosderelojes.game.model.Answers

@Entity(tableName = "answers")
data class AnswersEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 1,
    val questionId: Int,
    val content: String,
    val isCorrectAnswer: Boolean,
    val level: Int
) {

}
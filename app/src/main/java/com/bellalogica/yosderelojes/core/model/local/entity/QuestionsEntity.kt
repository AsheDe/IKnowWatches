package com.bellalogica.yosderelojes.core.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions" )
data class QuestionsEntity (
    @PrimaryKey val id: Int= 1,
    val questionType: String,
    val questionText: String,
    val leadingImage: String,
    val level: Int
)
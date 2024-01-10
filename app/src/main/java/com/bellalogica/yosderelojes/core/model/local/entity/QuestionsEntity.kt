package com.bellalogica.yosderelojes.core.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuestionsEntity (
    @PrimaryKey val id: Int,
    val questionText: String
)
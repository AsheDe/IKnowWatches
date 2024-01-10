package com.bellalogica.yosderelojes.core.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["id"])
data class LevelsEntity(
     val id: Int,
    val numOfLevels: Int?,
    val lastPlayed: Int?)

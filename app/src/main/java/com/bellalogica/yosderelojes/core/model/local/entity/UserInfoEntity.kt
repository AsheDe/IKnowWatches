package com.bellalogica.yosderelojes.core.model.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInfoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 1,
    @ColumnInfo(name = "lives") val lives : Int,
    @ColumnInfo(name = "score") val score: Int,
    @ColumnInfo(name = "levelPlaying") val levelPlaying: Int
)

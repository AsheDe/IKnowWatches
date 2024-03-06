package com.bellalogica.yosderelojes.core.model.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "levels")
data class LevelsEntity(
   @PrimaryKey @ColumnInfo(name = "id") val id: Int,
   @ColumnInfo(name = "number_of_levels") val numberOfLevels: Int, )

package com.bellalogica.yosderelojes.core.model.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.bellalogica.yosderelojes.core.model.local.entity.QuestionsEntity

@Dao
interface QuestionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuestions(levelsEntity: QuestionsEntity)
}
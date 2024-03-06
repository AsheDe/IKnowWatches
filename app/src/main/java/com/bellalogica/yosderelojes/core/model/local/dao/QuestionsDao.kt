package com.bellalogica.yosderelojes.core.model.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bellalogica.yosderelojes.core.model.local.entity.QuestionsEntity

@Dao
interface QuestionsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertQuestions(levelsEntity: List<QuestionsEntity>)

    @Query("Select * from questions where level = :nivel")
    fun obtenerPreguntasDeNivel(nivel: Int) : List<QuestionsEntity>
}
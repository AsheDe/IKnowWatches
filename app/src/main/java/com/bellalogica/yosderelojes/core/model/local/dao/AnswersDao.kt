package com.bellalogica.yosderelojes.core.model.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bellalogica.yosderelojes.core.model.local.entity.AnswersEntity

@Dao
interface AnswersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAnswers(answers: List<AnswersEntity>)

    @Query("SELECT * FROM answers WHERE level=:nivel")
    fun obtenerRespuestasDeNivel(nivel: Int) : List<AnswersEntity>
}
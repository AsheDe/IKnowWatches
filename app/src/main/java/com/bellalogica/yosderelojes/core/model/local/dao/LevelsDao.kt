package com.bellalogica.yosderelojes.core.model.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bellalogica.yosderelojes.core.model.local.entity.LevelsEntity

@Dao
interface LevelsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLevels(levelsEntity: LevelsEntity)

    @Query("SELECT * FROM levels LIMIT 1")
    fun getNumberOfLocalLevels(): LevelsEntity?

}
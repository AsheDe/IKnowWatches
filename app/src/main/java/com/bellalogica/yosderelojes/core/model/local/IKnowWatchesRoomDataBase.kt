package com.bellalogica.yosderelojes.core.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bellalogica.yosderelojes.core.model.local.dao.LevelsDao
import com.bellalogica.yosderelojes.core.model.local.dao.QuestionsDao
import com.bellalogica.yosderelojes.core.model.local.entity.LevelsEntity
import com.bellalogica.yosderelojes.core.model.local.entity.QuestionsEntity

@Database(entities = [LevelsEntity::class], version = 1, exportSchema = false)
abstract class IKnowWatchesRoomDataBase: RoomDatabase() {

    abstract val levelsDao: LevelsDao
    //abstract fun questionsDao(): QuestionsDao

    companion object {
        const val DATABASE_NAME = "iknowwatches_db"
    }
}

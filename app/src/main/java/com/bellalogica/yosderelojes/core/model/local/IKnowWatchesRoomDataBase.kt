package com.bellalogica.yosderelojes.core.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bellalogica.yosderelojes.core.model.local.dao.AnswersDao
import com.bellalogica.yosderelojes.core.model.local.dao.LevelsDao
import com.bellalogica.yosderelojes.core.model.local.dao.QuestionsDao
import com.bellalogica.yosderelojes.core.model.local.dao.UserInfoDao
import com.bellalogica.yosderelojes.core.model.local.entity.AnswersEntity
import com.bellalogica.yosderelojes.core.model.local.entity.LevelsEntity
import com.bellalogica.yosderelojes.core.model.local.entity.QuestionsEntity
import com.bellalogica.yosderelojes.core.model.local.entity.UserInfoEntity

@Database(entities = [LevelsEntity::class, QuestionsEntity::class, AnswersEntity::class, UserInfoEntity::class], version = 1, exportSchema = false)
abstract class IKnowWatchesRoomDataBase: RoomDatabase() {

    abstract val levelsDao: LevelsDao
    abstract val questionsDao: QuestionsDao
    abstract val answersDao: AnswersDao
    abstract val userInfoDao: UserInfoDao

    //abstract fun questionsDao(): QuestionsDao

    companion object {
        const val DATABASE_NAME = "iknowwatches_db"
    }
}

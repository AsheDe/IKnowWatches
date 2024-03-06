package com.bellalogica.yosderelojes.core.model.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.bellalogica.yosderelojes.core.model.local.entity.UserInfoEntity

@Dao
interface UserInfoDao {
    @Insert(entity = UserInfoEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun upsertUserInfo(userinfo: UserInfoEntity)

    @Query("SELECT * FROM UserInfoEntity LIMIT 1")
    fun getUserInfo(): UserInfoEntity

}
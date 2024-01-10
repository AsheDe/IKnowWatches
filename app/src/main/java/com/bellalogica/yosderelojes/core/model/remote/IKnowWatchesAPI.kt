package com.bellalogica.yosderelojes.core.model.remote

import com.bellalogica.yosderelojes.core.model.remote.dto.GameInfo
import com.bellalogica.yosderelojes.core.model.remote.dto.GameLevelDto
import retrofit2.http.GET
import retrofit2.http.Path

interface IKnowWatchesAPI {

    @GET("/cantlevels")
    suspend fun getGameStart(): GameInfo

    @GET("/levels/{levelNumber}")
    suspend fun getLevel(@Path("levelNumber") levelNumber: String): GameLevelDto

    companion object {
        const val BASE_URL = "http://192.168.1.12:8080/"
    }
}
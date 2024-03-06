package com.bellalogica.yosderelojes.core.model.remote

import com.bellalogica.yosderelojes.core.model.remote.dto.GameInfo
import com.bellalogica.yosderelojes.core.model.remote.dto.GameLevelDto
import retrofit2.http.GET
import retrofit2.http.Path

interface IKnowWatchesAPI {

    @GET("/cantlevels")
    suspend fun getApiNumberOfLevels(): GameInfo

    @GET("/levels/{levelNumber}")
    suspend fun downloadLevel(@Path("levelNumber") levelNumber: String): GameLevelDto
}
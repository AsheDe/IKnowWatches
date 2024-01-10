package com.bellalogica.yosderelojes.core.domain.repository

import com.bellalogica.yosderelojes.game.model.Question
import com.bellalogica.yosderelojes.game.ui.UserState

interface IKnowWatchesRepository {
    suspend fun getNumOfLevels(): Result<Int>
    suspend fun getLastLevelPlayed(): Result<Int>

    suspend fun getLevel(levelNumber: Int): Result<List<Question>>

    suspend fun getCurrentPlayingGameLevel(): Result<List<Question>>
    suspend fun getUserInfo(): Result<UserState?>
}
package com.bellalogica.yosderelojes.core.domain.repository

import com.bellalogica.yosderelojes.game.model.Question
import com.bellalogica.yosderelojes.game.ui.UserState

interface IKnowWatchesRepository {
    suspend fun getLevelsToLoadFromApi(): Result<List<Int>>
    suspend fun getCurrentPlayingLevel(): Int
    suspend fun getQuestionsForLevel(level: Int): List<Question>
    suspend fun getUserInfo(): UserState
    suspend fun downloadLevels(list: List<Int>)
    suspend fun getApiNumberOfLevels(): Int
    suspend fun getLocalNumberOfLevels(): Int
}
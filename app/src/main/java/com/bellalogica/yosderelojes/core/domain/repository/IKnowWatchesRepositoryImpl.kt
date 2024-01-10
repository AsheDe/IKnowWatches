package com.bellalogica.yosderelojes.core.domain.repository

import com.bellalogica.yosderelojes.core.model.local.dao.LevelsDao
import com.bellalogica.yosderelojes.core.model.local.entity.LevelsEntity
import com.bellalogica.yosderelojes.core.model.remote.IKnowWatchesAPI
import com.bellalogica.yosderelojes.core.model.remote.toListOfQuestions
import com.bellalogica.yosderelojes.game.model.Question
import com.bellalogica.yosderelojes.game.ui.UserState
import retrofit2.HttpException

class IKnowWatchesRepositoryImpl(
    private val dbAccess: LevelsDao,
    private val remoteAPI: IKnowWatchesAPI
) : IKnowWatchesRepository {
    override suspend fun getNumOfLevels(): Result<Int> {
        return try {
            val remoteNumberOfLevels = remoteAPI.getGameStart().let {
                it.numberOfLevels
            }
            val numOfLevel = dbAccess.getNumOfLevels()
            val localNumberOfLevels = numOfLevel?.let { it.numOfLevels } ?: 0
            if (localNumberOfLevels == remoteNumberOfLevels) {
                Result.success(localNumberOfLevels)
            } else if (localNumberOfLevels < remoteNumberOfLevels) {
                val lastPlayed = numOfLevel?.lastPlayed ?: 1
                dbAccess.insertLevels(
                    LevelsEntity(1, remoteNumberOfLevels, lastPlayed)
                )
                Result.success(remoteNumberOfLevels)
            } else if (remoteNumberOfLevels != -1) {
                dbAccess.insertLevels(
                    LevelsEntity(1, remoteNumberOfLevels, 1)
                )
                Result.success(remoteNumberOfLevels)
            } else {
                Result.failure(Exception("No se pudo obtener el número de niveles"))
            }
        } catch (e: HttpException) {
            try {
                val numOfLevel = dbAccess.getNumOfLevels()
                val localNumberOfLevels = numOfLevel?.let { it.numOfLevels } ?: 0
                Result.success(localNumberOfLevels)
            } catch (e: Exception) {
                Result.failure(e)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getLastLevelPlayed(): Result<Int> {
        //Todo
        return Result.success(1)
    }

    override suspend fun getLevel(levelNumber: Int): Result<List<Question>> {
        return try {
            return remoteAPI.getLevel("1").let { response ->
                Result.success(response.toListOfQuestions())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCurrentPlayingGameLevel(): Result<List<Question>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserInfo(): Result<UserState?> {
        return Result.success(UserState(3, 200))
    }
}
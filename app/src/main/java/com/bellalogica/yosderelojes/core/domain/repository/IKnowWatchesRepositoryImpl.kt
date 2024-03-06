package com.bellalogica.yosderelojes.core.domain.repository

import android.util.Log
import com.bellalogica.yosderelojes.core.model.local.dao.AnswersDao
import com.bellalogica.yosderelojes.core.model.local.dao.LevelsDao
import com.bellalogica.yosderelojes.core.model.local.dao.QuestionsDao
import com.bellalogica.yosderelojes.core.model.local.dao.UserInfoDao
import com.bellalogica.yosderelojes.core.model.local.entity.LevelsEntity
import com.bellalogica.yosderelojes.core.model.local.entity.UserInfoEntity
import com.bellalogica.yosderelojes.core.model.remote.IKnowWatchesAPI
import com.bellalogica.yosderelojes.core.model.remote.dto.QuestionType
import com.bellalogica.yosderelojes.game.model.Answers
import com.bellalogica.yosderelojes.game.model.ImageWrapper
import com.bellalogica.yosderelojes.game.model.Question
import com.bellalogica.yosderelojes.game.ui.UserState
import retrofit2.HttpException

class IKnowWatchesRepositoryImpl(
    private val levelsDao: LevelsDao,
    private val questionsDao: QuestionsDao,
    private val answersDao: AnswersDao,
    private val userInfoDao: UserInfoDao,
    private val remoteAPI: IKnowWatchesAPI
) : IKnowWatchesRepository {
    override suspend fun getLevelsToLoadFromApi(): Result<List<Int>> {
        return try {
            val remoteNumberOfLevels = remoteAPI.getApiNumberOfLevels().let {
                it.numberOfLevels
            }
            val localLevel = levelsDao.getNumberOfLocalLevels() ?: LevelsEntity(1, 0)
            if(localLevel.numberOfLevels == 0) {
                levelsDao.insertLevels(LevelsEntity(1, remoteNumberOfLevels))
                userInfoDao.upsertUserInfo(UserInfoEntity(lives = 3, score = 0, levelPlaying = 1))
            } else if(remoteNumberOfLevels > localLevel.numberOfLevels) {
                levelsDao.insertLevels(LevelsEntity(1, remoteNumberOfLevels))
            } else if(remoteNumberOfLevels == localLevel.numberOfLevels) {
                return Result.success(emptyList())
            }
            Result.success(localLevel.numberOfLevels.rangeTo(remoteNumberOfLevels).toList())
        } catch (e: HttpException) {
                Result.success(emptyList())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getApiNumberOfLevels() : Int {
        return remoteAPI.getApiNumberOfLevels().let {
            it.numberOfLevels
        }
    }

    override suspend fun getLocalNumberOfLevels() : Int {
        return levelsDao.getNumberOfLocalLevels()?.numberOfLevels?: 0
    }

    override suspend fun getCurrentPlayingLevel(): Int  {
        return userInfoDao.getUserInfo().levelPlaying
    }

    override suspend fun downloadLevels(listOfLevels: List<Int>) {
            listOfLevels.forEach {
                val questionsOfLevel = remoteAPI.downloadLevel(it.toString())
                questionsDao.insertQuestions(questionsOfLevel.toListOfQuestionsEntity())
                answersDao.insertAnswers(questionsOfLevel.toListOfAnswersEntity())
            }
    }

    override suspend fun getQuestionsForLevel(playingLevel: Int): List<Question> {
        return try {
            val preguntasDelNivel = questionsDao.obtenerPreguntasDeNivel(playingLevel)
            val respuestasDelNivel = answersDao.obtenerRespuestasDeNivel(playingLevel)
            preguntasDelNivel.map { pregunta ->
                when (pregunta.questionType) {
                    QuestionType.FourTexts.name -> {
                        val respuestas = respuestasDelNivel.filter {
                            it.questionId == pregunta.id
                        }.map {
                            Answers.TextAnswer(it.content, it.isCorrectAnswer)
                        }
                        Question.FourTextsQuestion(
                            questionText = pregunta.questionText,
                            answers = respuestas,
                            leadingImage = ImageWrapper.NetworkImage(pregunta.leadingImage)
                        )
                    }
                    QuestionType.FourPictures.name -> {
                        val respuestas = respuestasDelNivel.filter {
                            it.questionId == pregunta.id
                        }.map {
                            Answers.ImageAnswer(
                                ImageWrapper.NetworkImage(it.content),
                                it.isCorrectAnswer
                            )
                        }
                        Question.FourPicturesQuestion(
                            questionText = pregunta.questionText,
                            answers = respuestas
                        )
                    }
                    else -> throw Exception("Tipo de pregunta no soportado")
                }
            }.shuffled()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getUserInfo(): UserState {
        return userInfoDao.getUserInfo().let {
            UserState(it.lives, it.score)
        }
    }
}
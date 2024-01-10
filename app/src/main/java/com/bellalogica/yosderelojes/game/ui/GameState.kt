package com.bellalogica.yosderelojes.game.ui

import com.bellalogica.yosderelojes.game.model.Question
import kotlinx.coroutines.Job

data class GameState(val listOfLevelQuestions: List<Question>, val userState: UserState)

data class UserState(var lives: Int = 0, var score: Int = 0)
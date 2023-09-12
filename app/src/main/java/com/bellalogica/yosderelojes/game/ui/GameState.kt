package com.bellalogica.yosderelojes.game.ui

import com.bellalogica.yosderelojes.game.model.Question

data class GameState(val listOfLevelQuestions: List<Question>, val userState: UserState)

data class UserState(val lives: Int = 0, val score: Int = 0)
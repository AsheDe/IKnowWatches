package com.bellalogica.yosderelojes.game.model.local

import com.bellalogica.yosderelojes.game.model.Question
import com.bellalogica.yosderelojes.game.ui.UserState
import kotlinx.coroutines.flow.Flow

interface DataBase {
    fun getGameLevel(): List<Question>

    fun getUserInfo(): UserState?
    fun hasMoreLevels(): Boolean
}
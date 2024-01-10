package com.bellalogica.yosderelojes.core.domain.repository

import com.bellalogica.yosderelojes.core.model.local.dao.LevelsDao
import com.bellalogica.yosderelojes.core.model.local.entity.LevelsEntity

class FakeLevelDao: LevelsDao {
    override fun insertLevels(levelsEntity: LevelsEntity) {

    }

    override fun getNumOfLevels(): LevelsEntity? {
        return LevelsEntity(1, 1, 1)
    }
}
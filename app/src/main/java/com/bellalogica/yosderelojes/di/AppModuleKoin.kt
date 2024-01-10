package com.bellalogica.yosderelojes.di

import androidx.room.Room
import com.bellalogica.yosderelojes.core.domain.repository.IKnowWatchesRepository
import com.bellalogica.yosderelojes.core.domain.repository.IKnowWatchesRepositoryImpl
import com.bellalogica.yosderelojes.core.model.local.IKnowWatchesRoomDataBase
import com.bellalogica.yosderelojes.core.model.remote.IKnowWatchesAPI
import com.bellalogica.yosderelojes.game.ui.GameViewModel
import com.bellalogica.yosderelojes.start.ui.StartScreenViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModuleKoin = module {
    single <IKnowWatchesAPI> {
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(IKnowWatchesAPI.BASE_URL).build().create(IKnowWatchesAPI::class.java)
    }

    single <IKnowWatchesRoomDataBase> {
        Room.databaseBuilder(
            androidApplication(),
            IKnowWatchesRoomDataBase::class.java,
            IKnowWatchesRoomDataBase.DATABASE_NAME
        ).build()
    }

    single <IKnowWatchesRepository> {
        IKnowWatchesRepositoryImpl(get<IKnowWatchesRoomDataBase>().levelsDao, get())
    }

    viewModel {
        StartScreenViewModel(get())
    }
    viewModel {
        GameViewModel(get())
    }
}

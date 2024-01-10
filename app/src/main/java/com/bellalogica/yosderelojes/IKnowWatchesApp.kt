package com.bellalogica.yosderelojes

import android.app.Application
import com.bellalogica.yosderelojes.di.appModuleKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin


class IKnowWatchesApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@IKnowWatchesApp)
            modules(appModuleKoin)
        }
    }
}

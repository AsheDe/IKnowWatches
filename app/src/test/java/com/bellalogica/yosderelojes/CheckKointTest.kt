package com.bellalogica.yosderelojes

import com.bellalogica.yosderelojes.di.appModuleKoin
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.verify.verify

class CheckKointTest: KoinTest {

    @Test
    fun checkModules() {
        appModuleKoin.verify()
    }

}
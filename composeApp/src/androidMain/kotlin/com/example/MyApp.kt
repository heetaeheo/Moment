package com.example

import android.app.Application
import com.example.di.androidModule
import com.example.di.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(
                commonModule,
                androidModule,
            )
        }
    }
}
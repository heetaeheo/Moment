package com.example.sharedmodule.di

import com.example.di.commonModule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

object IosKoinStarter {
    fun start() {
        startKoin {
            modules(
                commonModule,
                iosModule,
            )
        }
    }

    fun stop() {
        stopKoin()
    }
}
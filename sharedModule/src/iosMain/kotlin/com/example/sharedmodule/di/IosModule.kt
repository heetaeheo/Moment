package com.example.sharedmodule.di

import com.example.data.local.MomentDatabase
import com.example.data.local.buildDatabase
import getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

val iosModule: Module = module {
    single<MomentDatabase> { buildDatabase(getDatabaseBuilder()) }
    single { get<MomentDatabase>().chatMessageDao() }
}
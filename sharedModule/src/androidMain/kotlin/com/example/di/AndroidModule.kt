package com.example.di

import com.example.data.local.MomentDatabase
import com.example.data.local.buildDatabase
import com.example.data.local.getDatabaseBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val androidModule: Module = module {
    single<MomentDatabase> { buildDatabase(getDatabaseBuilder(androidContext())) }
    single { get<MomentDatabase>().chatMessageDao() }
}
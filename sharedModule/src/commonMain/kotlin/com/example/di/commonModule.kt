package com.example.di

import com.example.core.DateTimeProvider
import com.example.core.DateTimeProviderImpl
import com.example.data.repository.ChatRepositoryImpl
import com.example.domain.repository.ChatRepository
import kotlinx.datetime.TimeZone
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule: Module = module {
    single<ChatRepository> { ChatRepositoryImpl(get(), get()) }
    single<DateTimeProvider> { DateTimeProviderImpl(TimeZone.of("Asia/Seoul")) }
}
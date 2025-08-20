package com.example.domain.repository

import com.example.domain.model.ChatModel
import kotlinx.collections.immutable.ImmutableList

interface ChatRepository {
    suspend fun addUserMessage(message: String, dateKey: String)
    suspend fun addGeminiMessage(message: String, dateKey: String, responseTime: Long)
    suspend fun getMessages(dateKey: String): ImmutableList<ChatModel>
    suspend fun clearAll()
}

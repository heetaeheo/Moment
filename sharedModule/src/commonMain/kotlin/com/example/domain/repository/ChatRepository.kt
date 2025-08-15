package com.example.domain.repository

import com.example.data.local.ChatMessageEntity

interface ChatRepository {
    suspend fun addUserMessage(message: String, dateKey: String)
    suspend fun addGeminiMessage(message: String, dateKey: String, responseTime: Long)
    suspend fun getMessages(dateKey: String): List<ChatMessageEntity>
    suspend fun clearAll()
}

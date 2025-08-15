package com.example.data.repository

import com.example.core.DateTimeProvider
import com.example.data.local.ChatMessageDao
import com.example.data.local.ChatMessageEntity
import com.example.data.local.MessageType
import com.example.domain.repository.ChatRepository
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class ChatRepositoryImpl(
    private val dao: ChatMessageDao,
    private val dateTime: DateTimeProvider
): ChatRepository {
    override suspend fun addUserMessage(message: String, dateKey: String) {
        dao.insertMessage(
            ChatMessageEntity(
                message = message,
                timestamp = dateTime.now(),
                type = MessageType.USER,
                dateKey = dateKey
            )
        )
    }

    override suspend fun addGeminiMessage(
        message: String,
        dateKey: String,
        responseTime: Long
    ) {
        dao.insertMessage(
            ChatMessageEntity(
                message = message,
                timestamp = dateTime.now(),
                type = MessageType.GEMINI,
                dateKey = dateKey,
                geminiResponseTime = responseTime
            )
        )
    }

    override suspend fun getMessages(dateKey: String): List<ChatMessageEntity> {
        return dao.selectByDate(dateKey)
    }

    override suspend fun clearAll() {
        dao.deleteAll()
    }

}
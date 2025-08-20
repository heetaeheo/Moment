package com.example.data.repository

import com.example.core.DateTimeProvider
import com.example.data.local.ChatMessageDao
import com.example.data.local.ChatMessageEntity
import com.example.domain.mapper.toChatModel
import com.example.domain.model.ChatModel
import com.example.domain.repository.ChatRepository
import com.example.domain.type.MessageType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
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

    override suspend fun getMessages(dateKey: String): ImmutableList<ChatModel> {
        return dao.selectByDate(dateKey).map { it.toChatModel() }.toImmutableList()
    }

    override suspend fun clearAll() {
        dao.deleteAll()
    }

}
package com.example.data.local

class ChatMessageDatabase(
    private val databae: MomentDatabase
) {

    private val queries = database.chatMessageQueries

    suspend fun insert(message: ChatMessage) {
        queries.insertMessage(
            message = message.message,
            timestamp = message.timestamp,
            type = message.type.name,
            dateKey = message.dateKey,
            geminiResponseTime = message.geminiResponseTime
        )
    }

    suspend fun getMessagesByDate(dateKey: String): List<ChatMessage> {
        return queries.selectByDate(dateKey)
            .executeAsList()
            .map {
                ChatMessage(
                    id = it.id,
                    message = it.message,
                    timestamp = it.timestamp,
                    type = MessageType.valueOf(it.type),
                    dateKey = it.dateKey,
                    geminiResponseTime = it.geminiResponseTime
                )
            }
    }

    suspend fun getAll(): List<ChatMessage> {
        return queries.selectAll()
            .executeAsList()
            .map {
                ChatMessage(
                    id = it.id,
                    message = it.message,
                    timestamp = it.timestamp,
                    type = MessageType.valueOf(it.type),
                    dateKey = it.dateKey,
                    geminiResponseTime = it.geminiResponseTime
                )
            }
    }

    suspend fun deleteAll() {
        queries.deleteAll()
    }

}
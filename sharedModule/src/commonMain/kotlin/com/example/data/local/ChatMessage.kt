package com.example.data.local

enum class MessageType {
    USER, GEMINI
}

data class ChatMessage(
    val id: Long,
    val message: String,
    val timestamp: Long,
    val type: MessageType,
    val dateKey: String,
    val geminiResponseTime: Long? = null
)
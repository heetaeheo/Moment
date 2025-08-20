package com.example.domain.model

import androidx.compose.runtime.Immutable
import com.example.domain.type.MessageType

@Immutable
data class ChatModel(
    val id: Long = 0L,
    val message: String,
    val timestamp: Long,
    val type: MessageType,
    val dateKey: String,
    val geminiResponseTime: Long? = null
)
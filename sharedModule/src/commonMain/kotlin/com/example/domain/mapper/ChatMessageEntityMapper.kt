package com.example.domain.mapper

import com.example.data.local.ChatMessageEntity
import com.example.domain.model.ChatModel

fun ChatMessageEntity.toChatModel(): ChatModel {
    return ChatModel(
        id = id,
        message = message,
        timestamp = timestamp,
        type = type,
        dateKey = dateKey,
        geminiResponseTime = geminiResponseTime
    )
}

fun ChatModel.toChatMessageEntity(): ChatMessageEntity {
    return ChatMessageEntity(
        id = id,
        message = message,
        timestamp = timestamp,
        type = type,
        dateKey = dateKey,
        geminiResponseTime = geminiResponseTime
    )
}
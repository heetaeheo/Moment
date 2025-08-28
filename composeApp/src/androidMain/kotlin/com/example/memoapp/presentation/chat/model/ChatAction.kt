package com.example.memoapp.presentation.chat.model

import com.example.domain.model.ChatModel

sealed interface ChatAction {
    data object EnterChatScreen: ChatAction
    data class SendChat(val chatModel: ChatModel): ChatAction
    data class ChangeDate(val dateKey: String): ChatAction
}
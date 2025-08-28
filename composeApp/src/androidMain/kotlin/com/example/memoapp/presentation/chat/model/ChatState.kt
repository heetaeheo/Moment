package com.example.memoapp.presentation.chat.model

import com.example.domain.model.ChatModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class ChatState(
    val dateKey: String = "",
    val todayKey: String = "",
    val dateFormatted: String = "",
    val chatModelList: ImmutableList<ChatModel> = persistentListOf(),
    val isMenuOpen: Boolean = false
)
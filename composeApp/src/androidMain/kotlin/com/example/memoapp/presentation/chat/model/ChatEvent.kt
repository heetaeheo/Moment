package com.example.memoapp.presentation.chat.model

sealed interface ChatEvent {
    data class ShowToastMessage(val message: String) : ChatEvent
}
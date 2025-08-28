package com.example.memoapp.presentation.chat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.DateTimeProvider
import com.example.domain.repository.ChatRepository
import com.example.memoapp.presentation.chat.model.ChatAction
import com.example.memoapp.presentation.chat.model.ChatEvent
import com.example.memoapp.presentation.chat.model.ChatState
import com.example.memoapp.presentation.util.toDateKey
import com.example.memoapp.presentation.util.toLocalDateOrNull
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ChatViewModel(
    private val chatRepository: ChatRepository,
    private val dateTimeProvider: DateTimeProvider
): ViewModel() {

    var uiState by mutableStateOf(ChatState())
        private set

    private val eventChannel = Channel<ChatEvent>()
    val events = eventChannel.receiveAsFlow()



    fun onAction(action: ChatAction) {
        when (action) {
            ChatAction.EnterChatScreen -> loadInitialMessages()
            is ChatAction.SendChat -> onUserMessage(action.chatModel.message)
            is ChatAction.ChangeDate -> loadMessageForDate(action.dateKey)
        }
    }

    fun loadPreviousDate() {
        val prevKey = uiState.dateKey.toLocalDateOrNull()?.minusDays(1)?.toDateKey() ?: return
        onAction(ChatAction.ChangeDate(prevKey))
    }

    fun loadNextDate() {
        val nextKey = uiState.dateKey.toLocalDateOrNull()?.plusDays(1)?.toDateKey() ?: return
        onAction(ChatAction.ChangeDate(nextKey))
    }

    private fun loadInitialMessages() {
        viewModelScope.launch {
            val todayKey = dateTimeProvider.currentDateKey()
            val messages = chatRepository.getMessages(todayKey)
            uiState = uiState.copy(
                chatModelList = messages,
                dateKey = todayKey,
                todayKey = todayKey,
                dateFormatted = formatDateForUi(todayKey)
            )
        }
    }

    private fun onUserMessage(message: String) {
        viewModelScope.launch {
            val todayKey = dateTimeProvider.currentDateKey()
            chatRepository.addUserMessage(message, todayKey)
            loadInitialMessages()
        }
    }

    private fun loadMessageForDate(dateKey: String) {
        viewModelScope.launch {
            val todayKey = dateTimeProvider.currentDateKey()

            if (dateKey > todayKey) {
                eventChannel.send(ChatEvent.ShowToastMessage("미래의 메모장은 가져올 수 없습니다."))
                return@launch
            }

            val messages = chatRepository.getMessages(dateKey)
            uiState = uiState.copy(
                chatModelList = messages,
                dateKey = dateKey,
                dateFormatted = formatDateForUi(dateKey)
            )
        }
    }

    private fun formatDateForUi(dateKey: String): String {
        val parts = dateKey.split("-")
        return if (parts.size == 3) "${parts[1].toInt()}월 ${parts[2].toInt()}일" else dateKey
    }
}
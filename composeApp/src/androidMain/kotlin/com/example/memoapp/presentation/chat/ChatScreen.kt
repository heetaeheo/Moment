package com.example.memoapp.presentation.chat

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.domain.model.ChatModel
import com.example.domain.type.MessageType
import com.example.memoapp.presentation.chat.component.ChatBubble
import com.example.memoapp.presentation.chat.model.ChatAction
import com.example.memoapp.presentation.chat.model.ChatEvent
import com.example.memoapp.presentation.util.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    viewModel: ChatViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val uiState = viewModel.uiState
    var inputText by remember { mutableStateOf("") }

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is ChatEvent.ShowToastMessage -> {
                Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onAction(ChatAction.EnterChatScreen)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Default.KeyboardArrowLeft, contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = uiState.dateFormatted.ifBlank { "로딩 중..." }
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            Icons.Default.KeyboardArrowRight, contentDescription = null
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {

                    }) {
                        Icon(Icons.Default.Menu, contentDescription = "메뉴 열기")
                    }
                },
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(Icons.Default.DateRange, contentDescription = "메뉴 열기")
                    }
                }
            )
        },
        bottomBar = {
            Row(
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    modifier = Modifier.weight(1f),
                    value = inputText,
                    onValueChange = { inputText = it },
                    placeholder = { Text("메시지를 입력하세요") }
                )
                IconButton(
                    onClick = {
                        if (inputText.isNotBlank()) {
                            val message = ChatModel(
                                message = inputText,
                                timestamp = System.currentTimeMillis(),
                                type = MessageType.USER,
                                dateKey = uiState.dateKey
                            )
                            viewModel.onAction(ChatAction.SendChat(message))
                            inputText = ""
                        }
                    }
                ) {
                    Icon(Icons.Default.Send, contentDescription = "보내기")
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            reverseLayout = true,
            contentPadding = PaddingValues(8.dp)
        ){
            items(uiState.chatModelList.reversed()) { chatModel ->
                ChatBubble(
                    chatModel = chatModel,
                    isUser = chatModel.type == MessageType.USER
                )
            }
        }
    }
}
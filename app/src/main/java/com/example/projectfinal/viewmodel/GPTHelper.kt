package com.example.projectfinal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import kotlinx.coroutines.launch

class GPTHelper : ViewModel() {
    private var openAI: OpenAI? = null
    init {
        openAI = OpenAI("sk-proj-YlOKA4yoO453TPzL5DAGT3BlbkFJr7gx9I6erUuJB9HwkLa2")
    }

    @OptIn(BetaOpenAI::class)
    fun getGPTResponse(question: String, callBack: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val chatCompletionRequest = ChatCompletionRequest(
                        model = ModelId("gpt-3.5-turbo"),
                        messages = listOf(
                                ChatMessage(
                                        role = ChatRole.Assistant,
                                        content = question
                                )
                        )
                )
                val completion: ChatCompletion? = openAI?.chatCompletion(chatCompletionRequest)
                val response = completion?.choices?.first()?.message?.content
                callBack(response ?: "")
            } catch (e: Exception) {
                //callBack("//ERROR: ${e.cause?.message ?: ""}")
            }
        }
    }
}
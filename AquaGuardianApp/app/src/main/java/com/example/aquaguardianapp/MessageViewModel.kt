package com.example.aquaguardianapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit
class MessageViewModel : ViewModel() {
    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> get() = _messages

    fun loadMessages() {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getMessage()
                Log.d("MessageViewModel", "Response: $response")
                _messages.value = response
            } catch (e: HttpException) {
                // Log the response code and error body
                Log.e("MessageViewModel", "HTTP Exception: ${e.code()}\n${e.response()?.errorBody()?.string()}", e)
            } catch (e: Exception) {
                Log.e("MessageViewModel", "Error getting messages", e)
            }
        }
    }
}
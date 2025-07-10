package com.example.a63_coroutines


/**Created by Raviteja Emandi on 10,July,2025 **/

import androidx.lifecycle.*
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    private val _text = MutableLiveData("Loading...")
    val text: LiveData<String> = _text

    fun loadData() {
        viewModelScope.launch {
            val userDeferred = async { fetchUser() }
            val postsDeferred = async { fetchPosts() }

            val user = userDeferred.await()
            val posts = postsDeferred.await()

            _text.value = "$user\nPosts:\n${posts.joinToString("\n")}"
        }
    }

    private suspend fun fetchUser(): String {
        delay(1000)
        return "👤 User: Alice"
    }

    private suspend fun fetchPosts(): List<String> {
        delay(1500)
        return listOf("📄 Post 1", "📄 Post 2", "📄 Post 3")
    }
}

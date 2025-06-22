package com.example.bankapp_mvvm_register.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bankapp_mvvm_register.data.model.Post
import com.example.bankapp_mvvm_register.data.repository.PostRepository
import kotlinx.coroutines.launch


/**Created by Raviteja Emandi on 22,June,2025 **/
class HomeViewModel(private val repository: PostRepository) : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    fun fetchPosts() {
        viewModelScope.launch {
            try {
                _posts.value = repository.getPosts()
            } catch (e: Exception) {
                _posts.value = emptyList()
            }
        }
    }
}

class HomeViewModelFactory(private val repository: PostRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}

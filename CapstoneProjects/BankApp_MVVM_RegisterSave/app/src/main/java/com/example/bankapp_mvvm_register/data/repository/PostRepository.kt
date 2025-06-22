package com.example.bankapp_mvvm_register.data.repository

import com.example.bankapp_mvvm_register.data.model.Post
import com.example.bankapp_mvvm_register.network.RetrofitClient


/**Created by Raviteja Emandi on 22,June,2025 **/
class PostRepository {
    suspend fun getPosts(): List<Post> {
        return RetrofitClient.apiService.getPosts()
    }
}

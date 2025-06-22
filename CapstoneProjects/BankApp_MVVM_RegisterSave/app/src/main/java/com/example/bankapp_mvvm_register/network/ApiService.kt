package com.example.bankapp_mvvm_register.network

import com.example.bankapp_mvvm_register.data.model.Post
import retrofit2.http.GET


/**Created by Raviteja Emandi on 22,June,2025 **/
interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}

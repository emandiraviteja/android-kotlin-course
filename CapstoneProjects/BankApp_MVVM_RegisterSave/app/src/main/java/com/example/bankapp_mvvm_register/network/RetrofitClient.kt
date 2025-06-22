package com.example.bankapp_mvvm_register.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**Created by Raviteja Emandi on 22,June,2025 **/
object RetrofitClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

package com.example.a14_oop.sealedClass


/**Created by Raviteja Emandi on 15,June,2025 **/
sealed class Result {
    data class Success(val data: String) : Result()
    data class Error(val message: String) : Result()
    object Loading : Result()
}
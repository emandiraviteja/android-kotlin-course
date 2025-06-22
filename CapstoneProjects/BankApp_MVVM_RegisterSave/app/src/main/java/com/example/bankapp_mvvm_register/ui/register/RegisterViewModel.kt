package com.example.bankapp_mvvm_register.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bankapp_mvvm_register.model.User
import com.example.bankapp_mvvm_register.repository.UserRepository


/**Created by Raviteja Emandi on 22,June,2025 **/
class RegisterViewModel(private val repository: UserRepository) : ViewModel() {

    suspend fun checkUserExists(email: String): Boolean {
        return repository.getUserByEmail(email) != null
    }

    suspend fun registerUser(user: User) {
        repository.insertUser(user)
    }
}

class RegisterViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(repository) as T
    }
}
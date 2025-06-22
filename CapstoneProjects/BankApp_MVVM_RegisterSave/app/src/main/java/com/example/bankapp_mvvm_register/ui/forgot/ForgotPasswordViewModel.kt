package com.example.bankapp_mvvm_register.ui.forgot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bankapp_mvvm_register.model.User
import com.example.bankapp_mvvm_register.repository.UserRepository


/**Created by Raviteja Emandi on 22,June,2025 **/
class ForgotPasswordViewModel(private val repository: UserRepository) : ViewModel() {
    suspend fun getUserByEmail(email: String): User? {
        return repository.getUserByEmail(email)
    }

    suspend fun updateUser(user: User) {
        repository.updateUser(user)
    }
}

class ForgotPasswordViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ForgotPasswordViewModel(repository) as T
    }
}
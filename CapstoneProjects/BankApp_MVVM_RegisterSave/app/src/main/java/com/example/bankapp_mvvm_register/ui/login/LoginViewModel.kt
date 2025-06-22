package com.example.bankapp_mvvm_register.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bankapp_mvvm_register.model.User
import com.example.bankapp_mvvm_register.repository.UserRepository


/**Created by Raviteja Emandi on 22,June,2025 **/
class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    suspend fun loginUser(email: String, password: String): User? {
        return repository.login(email, password)
    }

    suspend fun checkUserExists(email: String): Boolean {
        return repository.getUserByEmail(email) != null
    }
}

class LoginViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }
}

package com.example.bankapp_mvvm_register.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bankapp_mvvm_register.model.User
import com.example.bankapp_mvvm_register.repository.UserRepository


/**Created by Raviteja Emandi on 22,June,2025 **/
class ProfileViewModel(private val repository: UserRepository) : ViewModel() {

    suspend fun getUserById(userId: Int): User? {
        return repository.getUserById(userId)
    }

    suspend fun updateUser(user: User) {
        repository.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        repository.deleteUser(user)
    }
}

class ProfileViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository) as T
    }
}

package com.example.a52_roomdatabase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.a52_roomdatabase.data.AppDatabase
import com.example.a52_roomdatabase.data.User
import com.example.a52_roomdatabase.repository.UserRepository
import kotlinx.coroutines.launch


/**Created by Raviteja Emandi on 24,June,2025 **/
class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository
    val allUsers: LiveData<List<User>>

    init {
        val userDao = AppDatabase.getInstance(application).userDao()
        repository = UserRepository(userDao)
        allUsers = repository.allUsers
    }

    fun insert(user: User) = viewModelScope.launch { repository.insert(user) }
    fun update(user: User) = viewModelScope.launch { repository.update(user) }
    fun delete(user: User) = viewModelScope.launch { repository.delete(user) }
}

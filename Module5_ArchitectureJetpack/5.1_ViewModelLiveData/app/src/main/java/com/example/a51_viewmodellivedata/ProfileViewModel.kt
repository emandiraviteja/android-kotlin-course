package com.example.a51_viewmodellivedata


/**Created by Raviteja Emandi on 24,June,2025 **/

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    private val _user = MutableLiveData<User>().apply {
        value = User("Ravi", "ravi@example.com", 25)
    }

    val user: LiveData<User> = _user

    fun updateUser(name: String, email: String, age: Int) {
        _user.value = User(name, email, age)
    }
}

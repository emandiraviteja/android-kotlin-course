package com.example.a53_databinding


/**Created by Raviteja Emandi on 24,June,2025 **/

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()
    val successMessage = MutableLiveData<String>()

    fun onSubmit() {
        val currentName = name.value ?: ""
        val currentEmail = email.value ?: ""

        if (currentName.isBlank() || currentEmail.isBlank()) {
            errorMessage.value = "Please fill all fields"
            successMessage.value = ""
        } else {
            errorMessage.value = ""
            successMessage.value = "Form submitted: $currentName, $currentEmail"
        }
    }
}
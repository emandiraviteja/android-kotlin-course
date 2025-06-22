package com.example.bankapp_mvvm_register.utils

import android.content.Context


/**Created by Raviteja Emandi on 22,June,2025 **/
class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    fun saveLogin(userId: Int) {
        prefs.edit().putInt("USER_ID", userId).apply()
    }

    fun getUserId(): Int = prefs.getInt("USER_ID", -1)

    fun logout() {
        prefs.edit().clear().apply()
    }

    fun isLoggedIn(): Boolean = getUserId() != -1
}
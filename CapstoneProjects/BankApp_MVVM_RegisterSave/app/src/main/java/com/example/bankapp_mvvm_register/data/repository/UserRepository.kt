package com.example.bankapp_mvvm_register.repository

import com.example.bankapp_mvvm_register.db.UserDao
import com.example.bankapp_mvvm_register.model.User


/**Created by Raviteja Emandi on 22,June,2025 **/
class UserRepository(private val userDao: UserDao) {

    suspend fun insertUser(user: User) = userDao.insertUser(user)

    suspend fun login(email: String, password: String): User? = userDao.login(email, password)

    suspend fun getUserByEmail(email: String): User? = userDao.getUserByEmail(email)

    suspend fun updateUser(user: User) = userDao.updateUser(user)

    suspend fun deleteUser(user: User) = userDao.deleteUser(user)

    suspend fun getUserById(userId: Int): User? {
        return userDao.getUserById(userId)
    }
}

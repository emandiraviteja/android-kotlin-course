package com.example.a52_roomdatabase.repository

import androidx.lifecycle.LiveData
import com.example.a52_roomdatabase.data.User
import com.example.a52_roomdatabase.data.UserDao


/**Created by Raviteja Emandi on 24,June,2025 **/
class UserRepository(private val userDao: UserDao) {
    val allUsers: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun insert(user: User) = userDao.insert(user)
    suspend fun update(user: User) = userDao.update(user)
    suspend fun delete(user: User) = userDao.delete(user)
}

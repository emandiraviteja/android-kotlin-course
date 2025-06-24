package com.example.a52_roomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


/**Created by Raviteja Emandi on 24,June,2025 **/
@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)
    @Update
    suspend fun update(user: User)
    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM users ORDER BY id DESC")
    fun getAllUsers(): LiveData<List<User>>
}

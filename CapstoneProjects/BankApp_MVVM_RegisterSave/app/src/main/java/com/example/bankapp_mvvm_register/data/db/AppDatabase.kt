package com.example.bankapp_mvvm_register.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bankapp_mvvm_register.model.Transaction
import com.example.bankapp_mvvm_register.model.User


/**Created by Raviteja Emandi on 22,June,2025 **/
@Database(entities = [User::class, Transaction::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "login_room_app_db"
                ).build().also { instance = it }
            }
        }
    }
}

package com.example.bankapp_mvvm_register.model

import androidx.room.Entity
import androidx.room.PrimaryKey


/**Created by Raviteja Emandi on 22,June,2025 **/
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String,
    val password: String,
    val phone: String
)

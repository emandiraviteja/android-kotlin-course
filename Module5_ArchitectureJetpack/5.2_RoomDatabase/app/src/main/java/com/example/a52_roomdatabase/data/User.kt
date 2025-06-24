package com.example.a52_roomdatabase.data

import androidx.room.Entity
import androidx.room.PrimaryKey


/**Created by Raviteja Emandi on 24,June,2025 **/
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String
)

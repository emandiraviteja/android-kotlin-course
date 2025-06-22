package com.example.bankapp_mvvm_register.model

import androidx.room.Entity
import androidx.room.PrimaryKey


/**Created by Raviteja Emandi on 22,June,2025 **/
@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val amount: Double,
    val description: String,
    val date: String
)

package com.example.a41_recyclerview


/**Created by Raviteja Emandi on 02,July,2025 **/
//Why Use data class in Kotlin for Models?
//Kotlin's data class is specifically designed to hold data,
// and it automatically provides common functionality which is very useful
// when working with lists in RecyclerView.
data class Student(
    val id: Int,
    val name: String,
    val age: Int,
    val marks: Int
)

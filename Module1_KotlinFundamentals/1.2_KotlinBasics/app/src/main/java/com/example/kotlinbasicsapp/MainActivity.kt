package com.example.kotlinbasicsapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = "Teja"  // value cannot be changed later
        var age = 25  // variable can be updated
        age = 26

        //Arithmetic:
        val a = 10
        val b = 3

        println(a + b)  // 13
        println(a - b)  // 7
        println(a * b)  // 30
        println(a / b)  // 3 (Integer division)
        println(a % b)  // 1 (modulo)

        //Comparison:
        val x = 5
        val y = 10

        println(x > y)   // false
        println(x <= y)  // true

        //Logical:
        val isAdult = true
        val hasTicket = false

        println(isAdult && hasTicket)  // false (AND)
        println(isAdult || hasTicket)  // true (OR)

        //if statement
        val number = 10

        if (number > 0) {
            println("Positive number")
        } else {
            println("Zero or negative")
        }

        //if as an expression
        val max = if (a > b) a else b
        println("Max value is $max")

        //when statement (similar to switch)
        val day = 2

        val result = when(day) {
            1 -> "Monday"
            2 -> "Tuesday"
            3 -> "Wednesday"
            else -> "Invalid day"
        }
        println(result)

        //for loop (looping through a range)
        for (i in 1..5) {
            println("Count: $i")  // prints 1 to 5
        }

//        So the output will be:
//        Count: 1
//        Count: 2
//        Count: 3
//        Count: 4
//        Count: 5

        //for loop with downTo and step
        for (i in 10 downTo 1 step 2) {
            println(i)  // prints 10, 8, 6, 4, 2
        }

//        Output:
//        10
//        8
//        6
//        4
//        2

        //Iterating through a list
        val fruits = listOf("Apple", "Banana", "Cherry")
        for (fruit in fruits) {
            println(fruit)
        }

//        Output:
//        Apple
//        Banana
//        Cherry

        //While loop
        var i = 1
        while (i <= 3) {
            println("i = $i")
            i++
        }

//        Output:
//        i = 1
//        i = 2
//        i = 3
    }
}
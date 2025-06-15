package com.example.functions

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Invocation (Calling)
        val message = greet("Laalasa")
        println(message)   // Output: Hello, Laalasa!

        //Default Arguments
        greets()          // Prints: Hello, Guest!
        greets("Alice")   // Prints: Hello, Alice!

        //Named Arguments
        orderPizza(cheese = false, size = "Large")
        // Prints: Size: Large, Cheese: false

        //Lambda Expressions
        val sum = { a: Int, b: Int -> a + b }
        println(sum(3, 5))  // Prints 8

        //Lambdas as Parameters
        val numbers = listOf(1, 2, 3, 4)
        val evens = numbers.filter { it % 2 == 0 }
        println(evens)  // Prints: [2, 4]

    }

    private fun greet(name: String): String {
        return "Hello, $name!"
    }

    //Default Arguments
    private fun greets(name: String = "Guest") {
        println("Hello, $name!")
    }

    //Named Arguments
    private fun orderPizza(size: String = "Medium", cheese: Boolean = true) {
        println("Size: $size, Cheese: $cheese")
    }

}
package com.example.a14_oop

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a14_oop.dataClass.UserAdapter

class MainActivity : AppCompatActivity() {

    //data class
    private lateinit var userRecyclerView: RecyclerView

    //sealed class
    private lateinit var resultText: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var btnFetch: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Creating an Object (Instance):
        val person = Person()
        person.name = "Laalasa"
        person.age = 25
        person.introduce()
        // Output: Hi, my name is Laalasa and I'm 25 years old.

        //Inheritance
        val dog = Dog()
        dog.sound()  // Output: Woof!

        //Polymorphism:
        val animal: Animal = Dog()
        animal.sound()  // Output: Woof! (runtime decides actual method)

        //Implementing Interface:
        class Car : Drivable {
            override fun drive() {
                println("Car is driving")
            }
        }

        val car = Car()
        car.drive()  // Output: Car is driving

        //Data class
        userRecyclerView = findViewById(R.id.recyclerView)

        val users = listOf(
            User("Ravi", 25),
            User("Anita", 30),
            User("Kiran", 22),
            User("Meena", 28)
        )

        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.adapter = UserAdapter(users)
        //Data class

        //sealed
        resultText = findViewById(R.id.resultText)
        progressBar = findViewById(R.id.progressBar)
        btnFetch = findViewById(R.id.btnFetch)

        btnFetch.setOnClickListener {
            showResult(com.example.a14_oop.sealedClass.Result.Loading)

            // Simulate API call delay
            Handler(Looper.getMainLooper()).postDelayed({
                val success = (0..1).random() == 1
                val result = if (success) {
                    com.example.a14_oop.sealedClass.Result.Success("Data loaded successfully!")
                } else {
                    com.example.a14_oop.sealedClass.Result.Error("Failed to load data.")
                }
                showResult(result)
            }, 2000)
        }
        //sealed

    }

    //Class Declaration:
    class Person {
        var name: String = ""
        var age: Int = 0
        fun introduce() {
            println("Hi, my name is $name and I'm $age years old.")
        }
    }

    //Primary constructor
    class Student(val name: String, var age: Int) {
    }

    fun main() {
        val s = Student("Ravi", 21)
        println("${s.name} is ${s.age} years old")
    }

    //Secondary constructor
    class Book {
        var title: String
        var author: String

        constructor(title: String, author: String) {
            this.title = title
            this.author = author
        }
    }
    fun main1() {
        val b = Book("Kotlin Basics", "JetBrains")
        println("${b.title} by ${b.author}")
    }

    //Initializer Block (init)
    class User(val name: String, var age: Int) {
        init {
            println("Person Created: $name is $age years old")
        }
    }
    fun main2() {
        val p = User("Anita", 22)
    }

    //Primary + Secondary Constructor Together
    class Car(val brand: String) {
        var year: Int = 0

        constructor(brand: String, year: Int): this(brand) {
            this.year = year
        }
    }

    fun main3() {
        val c = Car("Toyota", 2020)
        println("${c.brand} - ${c.year}")
    }

    //Inheritance:
    open class Animal {
        open fun sound() {
            println("Animal sound")
        }
    }

    class Dog : Animal() {
        override fun sound() {
            println("Woof!")
        }
    }

    //Interfaces
    interface Drivable {
        fun drive()
    }

    data class Employee(val name: String, val age: Int) {
    }

    fun main4() {
        val u1 = Employee("Ravi", 25)
        val u2 = u1.copy(age = 26)
        println(u1) // Output: User(name=Ravi, age=25)
        println(u2) // Output: User(name=Ravi, age=26)
    }

    //sealed class
    private fun showResult(result: com.example.a14_oop.sealedClass.Result) {
        when (result) {
            is com.example.a14_oop.sealedClass.Result.Loading -> {
                progressBar.visibility = ProgressBar.VISIBLE
                resultText.text = "Loading..."
            }
            is com.example.a14_oop.sealedClass.Result.Success -> {
                progressBar.visibility = ProgressBar.GONE
                resultText.text = "✅ ${result.data}"
            }
            is com.example.a14_oop.sealedClass.Result.Error -> {
                progressBar.visibility = ProgressBar.GONE
                resultText.text = "❌ ${result.message}"
            }
        }
    }
    //sealed class
}
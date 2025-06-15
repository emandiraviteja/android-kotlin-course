package com.example.kotlinfundamentalsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val developerName = "Kotlin Developer"
        val greeting = "Hello, $developerName!"

        val textView: TextView = findViewById(R.id.tvGreeting)
        textView.text = greeting
    }
}

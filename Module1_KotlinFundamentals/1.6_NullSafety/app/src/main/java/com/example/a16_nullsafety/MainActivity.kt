package com.example.a16_nullsafety

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var outputText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        outputText = findViewById(R.id.outputText)
        val builder = StringBuilder()

        // Nullable type
        val name: String? = null
        builder.append("Name length: ${name?.length}\n") // safe call

        // Elvis operator
        val userInput: String? = null
        val result = userInput ?: "Default Value"
        builder.append("User input: $result\n")

        // Smart casting example
        val something: Any = "I'm a string"

        if (something is String) {
            // Smart cast to String
            builder.append("Smart cast length: ${something.length}\n")
        }

        // Unsafe cast (not recommended)
        val length = name?.length ?: -1
        builder.append("Length using safe elvis: $length\n")

        outputText.text = builder.toString()
    }
}
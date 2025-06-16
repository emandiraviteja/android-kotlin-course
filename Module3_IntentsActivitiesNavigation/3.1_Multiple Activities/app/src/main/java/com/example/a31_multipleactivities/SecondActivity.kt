package com.example.a31_multipleactivities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val name = intent.getStringExtra("name")
        val textReceived = findViewById<TextView>(R.id.textReceived)
        val btnBack = findViewById<Button>(R.id.btnBack)

        textReceived.text = "Hello, $name"

        btnBack.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("result", "Thank you, $name!")
            setResult(RESULT_OK, returnIntent)
            finish()
        }
    }
}
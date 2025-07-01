package com.example.a61_permissionsconnectivity

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val checkBtn: Button = findViewById(R.id.btnCheckNetwork)
        checkBtn.setOnClickListener {
            if (NetworkUtils.isNetworkAvailable(this)) {
                Toast.makeText(this, "Internet is available", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show()
            }
        }
    }
}
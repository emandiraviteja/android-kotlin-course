package com.example.a24_eventhandling

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLike = findViewById<Button>(R.id.btnLike)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val btnReset = findViewById<Button>(R.id.btnReset)
        val layout = findViewById<View>(android.R.id.content)

        btnLike.setOnClickListener {
            Toast.makeText(this, "You liked this!", Toast.LENGTH_SHORT).show()
        }

        btnSubmit.setOnClickListener {
            Snackbar.make(layout, "Form submitted", Snackbar.LENGTH_LONG)
                .setAction("Undo") {
                    Toast.makeText(this, "Submission undone", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

        btnReset.setOnClickListener {
            Toast.makeText(this, "Reset complete", Toast.LENGTH_SHORT).show()
        }
    }
}
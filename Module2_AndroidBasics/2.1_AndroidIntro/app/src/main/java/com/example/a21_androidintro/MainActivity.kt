package com.example.a21_androidintro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startServiceButton = findViewById<Button>(R.id.startServiceButton)
        startServiceButton.setOnClickListener {
            val serviceIntent = Intent(this, TimerService::class.java)
            startService(serviceIntent)
        }
    }

//    Testing
//    Run the app → Click "Start Timer Service".
//    Background logs will show countdown (check Logcat).
//    Set your emulator/device to low battery level to trigger BATTERY_LOW broadcast.
}
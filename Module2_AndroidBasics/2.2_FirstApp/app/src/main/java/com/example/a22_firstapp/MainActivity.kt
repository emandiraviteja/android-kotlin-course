package com.example.a22_firstapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("ActivityLife", "onCreate called")
        setContentView(R.layout.activity_main)
        
    }

    override fun onStart() {
        super.onStart()
        Log.e("ActivityLife", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.e("ActivityLife", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.e("ActivityLife", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.e("ActivityLife", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("ActivityLife", "onDestroy called")
    }
}
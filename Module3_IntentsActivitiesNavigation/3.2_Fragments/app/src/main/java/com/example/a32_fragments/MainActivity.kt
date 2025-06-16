package com.example.a32_fragments

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), MessageFragment.OnMessageSendListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load the fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MessageFragment())
            .commit()
    }

    override fun onMessageSend(message: String) {
        Toast.makeText(this, "Received: $message", Toast.LENGTH_SHORT).show()
    }
}
package com.example.a21_androidintro

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BatteryReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "⚠️ Battery is low!", Toast.LENGTH_LONG).show()
    }
}
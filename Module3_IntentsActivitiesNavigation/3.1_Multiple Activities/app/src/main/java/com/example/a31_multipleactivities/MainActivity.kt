package com.example.a31_multipleactivities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editName = findViewById<EditText>(R.id.editName)
        val btnSend = findViewById<Button>(R.id.btnSend)

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val msg = result.data?.getStringExtra("result")
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }
        }

        btnSend.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("name", editName.text.toString())
            launcher.launch(intent)
        }
    }
}
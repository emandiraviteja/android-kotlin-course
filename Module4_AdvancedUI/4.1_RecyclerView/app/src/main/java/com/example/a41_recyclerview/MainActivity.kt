package com.example.a41_recyclerview

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val adapter = NameAdapter { name ->
        Toast.makeText(this, "Clicked: $name", Toast.LENGTH_SHORT).show()
    }

    private val nameList = mutableListOf("Ravi", "Teja", "Nikhil", "Anusha")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.submitList(nameList.toList()) // Submit initial list

        btnAdd.setOnClickListener {
            val newName = "User ${Random.nextInt(100, 999)}"
            nameList.add(newName)
            adapter.submitList(nameList.toList()) // always submit a copy
        }
    }
}

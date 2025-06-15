package com.example.a15_collectionsgenerics

import androidx.appcompat.app.AppCompatActivity
import com.example.a15_collectionsgenerics.generic.MyBox
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var outputText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        outputText = findViewById(R.id.outputText)

        val builder = StringBuilder()

        // 🔸 List Example
        val fruits = listOf("Apple", "Banana", "Mango")           // Immutable
        val mutableFruits = mutableListOf("Grapes", "Orange")     // Mutable
        mutableFruits.add("Kiwi")

        builder.append("Immutable List: $fruits\n")
        builder.append("Mutable List after add: $mutableFruits\n\n")

        // 🔸 Set Example
        val set = setOf("A", "B", "A")                      // No duplicates
        val mutableSet = mutableSetOf("X", "Y")
        mutableSet.add("Z")

        builder.append("Immutable Set: $set\n")
        builder.append("Mutable Set: $mutableSet\n\n")

        // 🔸 Map Example
        val map = mapOf(1 to "One", 2 to "Two")
        val mutableMap = mutableMapOf(1 to "Uno", 2 to "Dos")
        mutableMap[3] = "Tres"

        builder.append("Immutable Map: $map\n")
        builder.append("Mutable Map: $mutableMap\n\n")

        // 🔸 Generics Example
        val intBox = MyBox(123)
        val stringBox = MyBox("Kotlin")
        builder.append("Generic Int Box: ${intBox.value}\n")
        builder.append("Generic String Box: ${stringBox.value}\n")

        outputText.text = builder.toString()
    }
}
# 📘 1.1 Introduction to Kotlin

This project introduces you to the Kotlin programming language in an Android environment.

## ✅ Notes

🕰️ Kotlin History
Kotlin was developed by JetBrains (the makers of IntelliJ IDEA) and released in 2011.
Kotlin project announced by JetBrains Feb 2016
Kotlin v1.0 released
May 2017 Google announced Kotlin as an official Android language
May 2019 Google made Kotlin the preferred Android language

✨ Kotlin Features (with examples):

-Concise Syntax
Kotlin reduces boilerplate code compared to Java.
val name = "Teja"  // Declares an immutable variable with type inference

-Null Safety
var name: String? = null  // Nullable type
println(name?.length)     // Safe call operator avoids NullPointerException
Extension Functions
fun String.firstChar(): Char = this[0]
println("Kotlin".firstChar())  // Output: K



## ✅ Topics Covered
- What is Kotlin and why it’s used for Android
- Basic syntax overview

## 📱 Project Overview

This app displays a simple greeting message using a Kotlin `val` string and string template:
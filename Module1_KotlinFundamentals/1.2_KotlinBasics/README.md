# 📘 1.2 Kotlin Basics

This lesson covers Kotlin’s essential building blocks: variables, data types, operators, conditionals, and loops.

---

## 🔤 Variables & Data Types

```kotlin
val name: String = "Teja"   // Immutable variable
var age: Int = 25           // Mutable variable

Common Data Types
Type	Example
Int	val a = 10
Long	val l = 100L
Float	val f = 10.5f
Double	val d = 20.0
Boolean	val flag = true
Char	val ch = 'A'
String	val name = "Teja"

➕ Operators

val a = 10
val b = 5

val sum = a + b       // 15
val diff = a - b      // 5
val prod = a * b      // 50
val quot = a / b      // 2
val mod = a % b       // 0

val isEqual = a == b  // false
val isGreater = a > b // true

🔁 Conditional Statements
if Expression

val score = 80
val result = if (score >= 50) "Pass" else "Fail"

when Statement

val day = 3
val dayName = when (day) {
    1 -> "Monday"
    2 -> "Tuesday"
    3 -> "Wednesday"
    else -> "Invalid day"
}

🔄 Loops
for Loop

for (i in 1..5) {
    println("Count: $i")
}

while Loop

var i = 1
while (i <= 5) {
    println("i is $i")
    i++
}

📱 Project Overview

This Android app (KotlinBasicsApp) uses a TextView to display:

    Variable values

    Condition evaluation

    Loop output

It's a simple app for learning how Kotlin logic translates to UI output.

🧠 Learning Outcomes

By completing this lesson, you’ll understand:

    How to use if, when, for, and while in Kotlin


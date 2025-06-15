# ✅ 1.3 Functions

This lesson introduces **functions** in Kotlin, including how to declare and invoke them, use default and named arguments, and write lambda expressions.

---

## 🔧 Function Declaration

In Kotlin, you declare a function using the `fun` keyword:

```kotlin
fun greet(name: String): String {
    return "Hello, $name!"
}

Explanation:

    fun — keyword to define a function

    greet — function name

    (name: String) — parameter with type

    : String — return type

    { ... } — function body

🏃 Function Invocation

Call the function like this:

val message = greet("Laalasa")
println(message)   // Output: Hello, Laalasa!

2️⃣ Default & Named Arguments
🎯 Default Arguments

You can assign default values to function parameters:

fun greet(name: String = "Guest") {
    println("Hello, $name!")
}

greet()          // Prints: Hello, Guest!
greet("Alice")   // Prints: Hello, Alice!

🎯 Named Arguments

You can specify arguments by name for better readability or to change order:

fun orderPizza(size: String = "Medium", cheese: Boolean = true) {
    println("Size: $size, Cheese: $cheese")
}

orderPizza(cheese = false, size = "Large")
// Output: Size: Large, Cheese: false

🔂 Lambda Expressions
🔧 What is a Lambda?

A lambda is an anonymous function — a function without a name — that can be:

    Assigned to a variable
    Passed as an argument

🧑‍🏫 Syntax

val sum = { a: Int, b: Int -> a + b }
println(sum(3, 5))  // Output: 8

📝 Structure:
{ a: Int, b: Int -> a + b }

    Parameters before ->
    Body after ->

🎯 Lambdas as Parameters

You can pass lambdas to functions like filter:

val numbers = listOf(1, 2, 3, 4)
val evens = numbers.filter { it % 2 == 0 }
println(evens)  // Output: [2, 4]

    filter takes a lambda of type (Int) -> Boolean

    it refers to each element in the list

🚀 Run It

This module includes code examples in a simple Android app showing:

    Basic function calls

    Default/named arguments in UI-triggered functions

    Lambda usage with lists and button clicks

🧠 Learning Outcomes

By the end of this lesson, you will be able to:

    Declare and invoke Kotlin functions

    Use default and named arguments

    Understand and apply lambda expressions




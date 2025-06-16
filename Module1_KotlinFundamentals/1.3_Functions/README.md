# ✅ 1.3 Functions

This lesson introduces **functions** in Kotlin, including how to declare and invoke them, use default and named arguments, and write lambda expressions.

---

## 👉️ Function Declaration

In Kotlin, you declare a function using the `fun` keyword:

```
fun greet(name: String): String {
    return "Hello, $name!"
}
```

**Explanation:**

    fun — keyword to define a function
    greet — function name
    (name: String) — parameter with type
    : String — return type
    { ... } — function body

## 👉 Function Invocation

Call the function like this:
```
val message = greet("Laalasa")
println(message)   // Output: Hello, Laalasa!
```

## 👉 Default & Named Arguments

**Default Arguments**
You can assign default values to function parameters:
```
fun greet(name: String = "Guest") {
    println("Hello, $name!")
}

greet()          // Prints: Hello, Guest!
greet("Alice")   // Prints: Hello, Alice!
```

**Named Arguments**
When calling a function, specify argument names for clarity or change order:
```
fun orderPizza(size: String = "Medium", cheese: Boolean = true) {
    println("Size: $size, Cheese: $cheese")
}

orderPizza(cheese = false, size = "Large")
// Output: Size: Large, Cheese: false
```

## 👉 Lambda Expressions

**What is a Lambda?**
A lambda is an anonymous function — a function without a name — that can be stored in a variable or passed around.

**Syntax:**
```
val sum = { a: Int, b: Int -> a + b }
println(sum(3, 5))  // Output: 8
```

**Structure:**
{ a: Int, b: Int -> a + b }

    Parameters before ->
    Body after ->

**Lambdas as Parameters**
You can pass lambdas to functions like filter:
```
val numbers = listOf(1, 2, 3, 4)
val evens = numbers.filter { it % 2 == 0 }
println(evens)  // Output: [2, 4]
```

    filter takes a lambda of type (Int) -> Boolean
    it refers to each element in the list

🚀 Run It

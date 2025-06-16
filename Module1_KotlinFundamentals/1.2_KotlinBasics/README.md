# ✅ 1.2 Kotlin Basics

This lesson covers Kotlin’s essential building blocks: variables, data types, operators, conditionals, and loops.

---

## 👉 Variables & Data Types

```
val name: String = "Teja"   // Immutable variable
var age: Int = 25           // Mutable variable
```

**Common Data Types in Kotlin**

| Data Type | Example Code        |
|-----------|---------------------|
| `Int`     | `val a = 10`        |
| `Long`    | `val l = 100L`      |
| `Float`   | `val f = 10.5f`     |
| `Double`  | `val d = 20.0`      |
| `Boolean` | `val flag = true`   |
| `Char`    | `val ch = 'A'`      |
| `String`  | `val name = "Teja"` |

## 👉  Operators

**Arithmetic:**
```
val a = 10
val b = 5

val sum = a + b       // 15
val diff = a - b      // 5
val prod = a * b      // 50
val quot = a / b      // 2
val mod = a % b       // 0
```

**Comparison:**
```
val x = 5
val y = 10

println(x > y)   // false
println(x <= y)  // true
```

**Logical:**
```
val isAdult = true
val hasTicket = false

println(isAdult && hasTicket)  // false (AND)
println(isAdult || hasTicket)  // true (OR)
```

## 👉 Conditional Statements
**if statement**
```
val number = 10
if (number > 0) {
    println("Positive number")
} else {
    println("Zero or negative")
}
```

 **if as an expression**
 ```
val max = if (a > b) a else b
println("Max value is $max")
```

**when statement (similar to switch)**
```
val day = 2
val result = when(day) {
    1 -> "Monday"
    2 -> "Tuesday"
    3 -> "Wednesday"
    else -> "Invalid day"
}
println(result)
```

## 👉 Loops
**for loop (looping through a range)**
```
for (i in 1..5) {
    println("Count: $i")  // prints 1 to 5
}
```

**for loop with downTo and step**
```
for (i in 10 downTo 1 step 2) {
    println(i)  // prints 10, 8, 6, 4, 2
}
```

**Iterating through a list**
```
val fruits = listOf("Apple", "Banana", "Cherry")
for (fruit in fruits) {
    println(fruit)
}
```

**while Loop**
```
var i = 1
while (i <= 3) {
    println("i = $i")
    i++
}
```

# ✅ 1.5 Collections & Generics in Kotlin

This lesson introduces Kotlin's powerful collection types and the concept of generics, enabling flexible and type-safe data handling.

---

## ✨ Collections Overview

Kotlin offers three primary collection types:

| Type  | Description |
|-------|-------------|
| **List** | Ordered collection (can contain duplicates) |
| **Set**  | Unordered collection with unique elements |
| **Map**  | Collection of key–value pairs |

Each collection type comes in two variants:

- **Immutable** → Read-only: `listOf()`, `setOf()`, `mapOf()`
- **Mutable** → Read/write: `mutableListOf()`, `mutableSetOf()`, `mutableMapOf()`

---

## 🔹 Lists

### ✅ Immutable List

```kotlin
val fruits = listOf("Apple", "Banana", "Cherry")

println(fruits[0])         // Output: Apple
println(fruits.size)       // Output: 3
println(fruits.contains("Banana"))  // Output: true

✏️ Mutable List

val animals = mutableListOf("Dog", "Cat")

animals.add("Elephant")
animals.remove("Cat")

println(animals)           // Output: [Dog, Elephant]

⚠️ You cannot add/remove elements in an immutable list.


🔸 Sets
✅ Immutable Set

val colors = setOf("Red", "Green", "Blue", "Red")
println(colors)            // Output: [Red, Green, Blue]

Duplicates are automatically removed.

✏️ Mutable Set

val numbers = mutableSetOf(1, 2, 3)

numbers.add(4)
numbers.remove(2)

println(numbers)           // Output: [1, 3, 4]

🔸 Maps (Key–Value Pairs)
✅ Immutable Map

val countryCodes = mapOf("IN" to "India", "US" to "United States")
println(countryCodes["IN"])  // Output: India

✏️ Mutable Map

val marks = mutableMapOf("Math" to 90, "English" to 85)

marks["Science"] = 88       // Add new key-value
marks.remove("Math")        // Remove a key

println(marks)              // Output: {English=85, Science=88}

🧠 Learning Outcomes

By the end of this lesson, you’ll be able to:

    Differentiate between mutable and immutable collections

    Use lists, sets, and maps effectively

    Perform common operations like add, remove, check, and access

    Understand the importance of data structure choice in Kotlin
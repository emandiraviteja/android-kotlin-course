# ✅ 1.6: Null Safety in Kotlin

Null safety is one of Kotlin’s key features, designed to eliminate the infamous `NullPointerException` (NPE) at compile time. Kotlin makes all types non-nullable by default, encouraging safer and more predictable code.

In Java, accessing a null value often results in the dreaded NullPointerException (NPE). Kotlin aims to eliminate this issue by making all types non-nullable by default, unless you explicitly declare otherwise.

⚠️ Kotlin's type system is designed to eliminate null references from your code, a common source of bugs.

---


## 👉 Why Null Safety?

**In Java:**

```
String name = null;
int len = name.length();  // ❌ NullPointerException
```
**In Kotlin:**
```
val name: String? = null
val len = name?.length  // ✅ Safe call — returns null
```
⚠️ Kotlin's type system enforces null checks at compile time to reduce runtime crashes.


## 👉 Nullable Types
By default, variables in Kotlin cannot hold null. If you want to allow null, you explicitly declare it with a ?.

**Syntax:**
```
var name: String = "Ravi"     // Non-nullable
var age: Int? = null          // Nullable type
```
**Example:**
```
var str: String = "Hello"
// str = null  ❌ Error

var str2: String? = "World"
str2 = null  // ✅ OK
```
📌 Use Case: Use nullable types when a value might not be present — e.g., form fields, API responses.

## 👉 Safe Call Operator (?.)

This lets you access a property or method only if the variable is not null.

**Syntax:**
```
val length = str2?.length
```
**Example:**
```
val name: String? = "Teja"
val length = name?.length      // ✅ 4

val nullName: String? = null
val len = nullName?.length     // ✅ null (no crash)
```
📌 Use Case: Use safe calls to avoid crashes from null dereferencing.


## 👉 Elvis Operator (?:)

Provides a default value when a nullable expression is null.

**Syntax:**
```
val result = str ?: "Default Value"
```
**Example:****
```
val name: String? = null
val finalName = name ?: "Guest"  // ✅ "Guest"
```
📌 Use Case: Return fallback/default values if the input is null.


## 👉 Smart Casting

Kotlin automatically casts a nullable variable to non-null after checking that it's not null.

**Syntax:**
```
if (str != null) {
    println(str.length) // Smart-casted to String
}
```
**Example:**
```
fun printLength(input: String?) {
    if (input != null) {
        println("Length: ${input.length}")  // Smart-casted
    } else {
        println("Input is null")
    }
}
```
📌 Use Case: Smart casting helps reduce the need for manual casts after null checks.


**Real-World Use Case**
```
class User(val name: String)

fun getUserName(user: User?): String {
    // Safe call + Elvis operator
    return user?.name ?: "Unknown"
}

fun main() {
    val user: User? = null
    println(getUserName(user))  // Output: Unknown
}
```
**Summary**
Feature	Use Case
?	Declare nullable variable
?.	Safe access to nullable properties
?:	Provide default values
Smart Casting	Auto-cast after null check

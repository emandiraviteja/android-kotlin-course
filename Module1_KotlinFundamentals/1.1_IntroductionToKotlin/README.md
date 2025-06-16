# ✅ 1.1 Introduction to Kotlin

This project introduces you to the Kotlin programming language in an Android environment.

---

## 👉️ Kotlin History

- **2011** – Kotlin developed by JetBrains (creators of IntelliJ IDEA)
- **Feb 2016** – Kotlin project officially announced
- **2016** – Kotlin v1.0 released
- **May 2017** – Google announced Kotlin as an official Android language
- **May 2019** – Google made Kotlin the preferred language for Android development

---

## 👉 Kotlin Features (with examples)

### ⚡️ Concise Syntax
Kotlin reduces boilerplate code compared to Java:
```
val name = "Teja" // Declares an immutable variable with type inference
```

### ⚡️ Null Safety

Avoids NullPointerException with nullable types and safe calls:
```
var name: String? = null
println(name?.length) // Safe call operator
```

### ⚡️ Extension Functions

Add new functionality to existing classes without inheritance:
```
fun String.firstChar(): Char = this[0]
println("Kotlin".firstChar()) // Output: K
```

### ⚡️ What Are Coroutines in Kotlin?

Coroutines allow you to write asynchronous, non-blocking code in a simple and sequential way — without complex callbacks or managing threads manually.
### ⚡️ Why Use Coroutines?

Because operations like:

    ✅ API calls
    ✅ Database access
    ✅ File I/O
    ✅ Timers and delays

### ⚡️️ Benefits of Coroutines:

    Asynchronous by default
    Keeps code clean and readable
    Avoids callback hell
    Structured concurrency for managing background tasks

### ⚡️ Where Can You Use Coroutines?

    Android:
        API requests (e.g., Retrofit)
        Database (e.g., Room)
        Background tasks (viewModelScope, lifecycleScope)

    Backend:
        Parallel processing
        Handling high traffic with non-blocking calls
        Standalone Kotlin/Java apps:
        Concurrent operations
        Simple async logic

### ⚡️ Coroutine Example:
```
// In Activity/Fragment
lifecycleScope.launch {
    delay(1000)
    updateUI()
}
```

**What it does:**

    Launches a coroutine in the lifecycle scope
    Waits 1 second (non-blocking delay)
    Calls updateUI() after delay

Use viewModelScope in ViewModels for lifecycle-aware background tasks.
Smart Casts & Type Inference
Smart Casts

Kotlin automatically casts types after checking with is.
```
fun demo(x: Any) {
    if (x is String) {
        println(x.length) // Smart cast: no manual cast needed
    }
}
```

**Example usage:**
```
demo("Hello Kotlin")
demo(1234) // Will not enter the if-block
```

## 👉️ Setting Up Kotlin in Android Studio
### ⚡️ Prerequisite

Android Studio installed (latest version recommended)

### ⚡️ Step-by-Step Guide

**Step 1: Create a New Android Project**

    Open Android Studio
    Click New Project
    Select Empty Activity
    Click Next

**Step 2: Configure Your Project**

    Name: MyFirstKotlinApp
    Language: Kotlin
    Minimum SDK: API 21 or higher
    Click Finish

**Step 3: Project Structure**

MainActivity.kt is generated automatically:
```
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("Hello from Kotlin!") // Prints to Logcat
    }
}
```

**Step 4: Run the App**

    Connect a device or emulator
    Click Run ▶️
    App installs and displays your greeting

✅ You now have a working Kotlin-based Android app!

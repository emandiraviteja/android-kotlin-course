# ✅  5.1 ViewModel & LiveData
---

## 👉️ Coroutines

- Coroutines are a way to write asynchronous and non-blocking code.
- They're like lightweight threads, managed by the Kotlin runtime.
- They allow you to perform tasks like network calls, file I/O, and database operations without blocking the main thread.

**Example Analogy:**
"Imagine you're cooking multiple dishes at once. You start boiling water (which takes time), but instead of standing there doing nothing, you start chopping vegetables. Coroutines let your program do similar multitasking."

### ⚡ Setting Up Coroutines
Goal: Setup required dependencies and environment.
Add to your `build.gradle:`
```
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
```
For Android:
```
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
```

### ⚡ Launching a Coroutine
Goal: Learn basic coroutine structure.
```
import kotlinx.coroutines.*

fun main() = runBlocking { // this is a coroutine
launch {
delay(1000L)
println("World!")
}
println("Hello,")
}
```

- runBlocking blocks the main thread and waits for coroutines inside to finish.
- launch starts a new coroutine.
- delay is a suspend function (non-blocking).

### ⚡ Suspending Functions

```
suspend fun fetchData(): String {
delay(2000) // simulates network delay
return "Data from server"
}
```

Use inside a coroutine:
```
launch {
val data = fetchData()
println(data)
}
```

### ⚡ Coroutine Builders
Different ways to create coroutines.

- `launch {}` – fire and forget
- `async {}` – returns a result (Deferred)
- `withContext(Dispatchers.IO) {}` – switch threads

```
val result = async {
fetchData()
}
println("Result: ${result.await()}")
```

### ⚡ Dispatchers
how coroutines run on different threads.

- launch(Dispatchers.Main) { /* UI thread */ }
- launch(Dispatchers.IO) { /* Disk/network I/O */ }
- launch(Dispatchers.Default) { /* CPU intensive */ }

### ⚡ Structured Concurrency
how child coroutines are tied to the parent.
```
coroutineScope {
launch {
delay(1000)
println("Task 1 done")
}
launch {
delay(500)
println("Task 2 done")
}
}
```

### ⚡ Exception Handling
Safely handle errors in coroutines.
```
val handler = CoroutineExceptionHandler { _, exception ->
println("Caught: $exception")
}

GlobalScope.launch(handler) {
throw RuntimeException("Boom!")
}
```

### ⚡ Real-World Example
```
fun main() = runBlocking {
val user = async { getUserFromApi() }
val posts = async { getPostsFromApi() }

    println("User: ${user.await()}")
    println("Posts: ${posts.await()}")
}
```

## 👉 Mini App: Fetch User & Posts (Coroutines in Android)
**Features:**
Uses `ViewModel + LiveData`
Uses `**viewModelScope.launch**` for coroutines
Fetches `"user"` and `"posts"` in parallel
Updates UI when data is fetched

Gradle Setup
In app/build.gradle:
```
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
}

android {
    compileSdk 34

    defaultConfig {
        applicationId "com.example.coroutinesdemo"
        minSdk 21
        targetSdk 34
        versionCode 1
        versionName "1.0"
    }

    buildFeatures {
        viewBinding true
    }
}

dependencies {
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0"
    implementation "androidx.appcompat:appcompat:1.6.1"
    implementation "androidx.constraintlayout:constraintlayout:2.1.4"
}
```

ViewModel: MainViewModel.kt
Activity: MainActivity.kt

**What Students Learn**
- How to use viewModelScope.launch for safe coroutine use in Android
- Parallel fetching with async
- UI updates using LiveData
- Simulating real network delays with delay()

**output:**
Initial State (Before Coroutine Completes):
Fetching data...

⏱ After ~1.5 Seconds (After fetchUser() and fetchPosts() finish in parallel):
👤 User: Alice
Posts:
📄 Post 1
📄 Post 2
📄 Post 3

**This shows that:**
- The user was fetched with delay(1000)
- The posts were fetched with delay(1500)
- They both ran in parallel using async, so total time = ~1.5 sec, not 2.5 sec
- The UI updated without blocking the main thread
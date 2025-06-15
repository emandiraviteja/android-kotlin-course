# 🟡 1. "Hello World" Project

## 📘 What is it?

The **"Hello World"** project is the traditional first step in learning any new programming language or platform. It helps you understand:

- How Android project files are organized
- How to define UI using XML
- How to write Kotlin code to control the UI

---

## 🔨 How to Create the Project

In Android Studio:  
**New Project → Empty Activity → Name:** `HelloWorldApp`

---

## 🧱 Project Files Overview

HelloWorldApp/
├── MainActivity.kt
├── res/
│ └── layout/
│ └── activity_main.xml
├── manifests/
│ └── AndroidManifest.xml


---

## 🧠 Why We Use XML for UI?

- ✅ **Separation of concerns:** UI is defined in XML, logic is in Kotlin
- ✅ Easy to **preview and edit visually** in Android Studio
- ✅ Reusable and adaptable across screen sizes and orientations

---

## 💬 Output:

The app launches and displays:

> **"Hello, World!"** in the center of the screen

---

# 🔁 2. Activity Lifecycle Overview

## 📘 What is Activity Lifecycle?

Every Android `Activity` goes through a **lifecycle** — from creation to destruction. Android automatically calls lifecycle methods when:

- the app is opened
- minimized
- rotated
- or closed

---

## 🔄 Lifecycle Methods

| Method       | Called When...                        |
|--------------|----------------------------------------|
| `onCreate()` | Activity is first created              |
| `onStart()`  | Activity becomes visible               |
| `onResume()` | Activity starts interacting with user  |
| `onPause()`  | User is leaving the activity           |
| `onStop()`   | Activity is no longer visible          |
| `onDestroy()`| Activity is destroyed                  |

---

## ✅ Syntax & Example

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d("ActivityLife", "onCreate called")
}

📌 Use Case:
Method	Common Use
onCreate()	Setup UI, initialize views, fetch data
onPause()	Save temporary state
onDestroy()	Clean up memory, close DB/files

🖥️ 3. Logging with Logcat
📘 What is Logcat?

Logcat is a built-in console in Android Studio that displays real-time logs from your device or emulator — system logs, crash logs, and app logs.

✅ Syntax:

Log.d("TAG", "Debug message")
Log.i("TAG", "Info message")
Log.w("TAG", "Warning message")
Log.e("TAG", "Error message")

💡 Example in MainActivity.kt

Log.d("HelloWorldApp", "App started successfully!")

📌 Use Case:

    ✅ Debug logic and flow

    ✅ Track lifecycle events

    ✅ Log user actions or state

    ✅ Detect crashes or unexpected behavior
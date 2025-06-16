# ✅ Module 2.1: Introduction to Android
📅 **Android Basics – Week 2**

---

## 👉 Android Architecture Overview

Android is based on a layered architecture divided into 4 main components:

|-----------------------------------|
Applications (Top Layer)
Android Framework Layer
|-----------------------------------|
Android Runtime + Libraries
|-----------------------------------|
Linux Kernel (Bottom Layer)

### ⚡ Linux Kernel
- Manages hardware drivers: camera, memory, keyboard, etc.
- Acts as a bridge between hardware and software.

### ⚡ Android Runtime (ART) + Core Libraries
- **ART**: Replaces Dalvik VM. Every app runs in its own process with its own ART instance.
- **Core Libraries**: Provide Java & Kotlin standard APIs.

### ⚡ Android Framework
- Provides APIs to build Android apps:  
  `ActivityManager`, `NotificationManager`, `View`, `ContentResolver`, etc.

### ⚡ Applications Layer
- Built-in apps (Phone, Contacts, SMS)
- User-installed apps

## 👉 Core Android Components

| Component        | Description                              | Example Use Case             |
|------------------|------------------------------------------|------------------------------|
| **Activity**      | UI screen – handles user interaction     | Login screen, Profile page   |
| **Service**       | Background tasks without UI              | Music playback, Downloads    |
| **BroadcastReceiver** | Responds to system-wide broadcast messages | Low battery alert, SMS received |
| **ContentProvider** | Shares data between apps               | Contacts, Calendar access    |


### ⚡Activity

**What is it?**  
A single, focused screen the user interacts with.

**Syntax:**
```
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
```
**Usage:**

    Each screen in your app = one Activity
    Launched via Intent

**Example:**
```
val intent = Intent(this, SecondActivity::class.java)
startActivity(intent)
```

### ⚡Service

What is it?
A component that runs in the background without a UI.

**Syntax:**
```
class MyService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Perform background task
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
```
**Usage:**

    Play music
    Upload files

**Start Service:**
```
val intent = Intent(this, MyService::class.java)
startService(intent)
```

### ⚡ BroadcastReceiver

**What is it?
Listens to system-wide or app-wide broadcast messages.

**Syntax:**
```
class BatteryReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Battery low!", Toast.LENGTH_SHORT).show()
    }
}
```
**Register in Manifest:**
```
<receiver android:name=".BatteryReceiver">
    <intent-filter>
        <action android:name="android.intent.action.BATTERY_LOW" />
    </intent-filter>
</receiver>
```

### ⚡ContentProvider

**What is it?
Used to share data between apps using a common interface.

**Syntax:**
```
val cursor = contentResolver.query(
    ContactsContract.Contacts.CONTENT_URI,
    null, null, null, null
)
```
**Usage:**

    Read contacts, SMS, media
    Create custom providers to expose app data

## 👉 Android Project Structure Overview

When you create a project, it looks like:

MyApp/
├── manifests/
│   └── AndroidManifest.xml
├── java/
│   └── com.example.myapp/
│       └── MainActivity.kt
├── res/
│   ├── layout/
│   │   └── activity_main.xml
│   ├── drawable/
│   ├── values/
│   │   ├── strings.xml
│   │   ├── colors.xml
│   │   └── styles.xml
├── build.gradle

### ⚡Folder Purposes
| Folder/File          | Purpose                                  |
|----------------------|------------------------------------------|
| `AndroidManifest.xml`| Declares app components, permissions     |
| `MainActivity.kt`     | Main logic file (entry point)            |
| `layout/`             | UI design XMLs                           |
| `drawable/`           | Images and shapes                        |
| `values/`             | Strings, colors, styles, dimensions      |
| `build.gradle`        | Dependencies and build settings          |


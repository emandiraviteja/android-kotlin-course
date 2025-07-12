# ✅  7.2 Background & WorkManager
---

## 👉 What are Background Tasks?

Background tasks are operations that don’t need the user to interact with the app directly.

**Examples:**
- Upload logs or analytics
- Download content in background
- Send a daily notification
- Sync data every 6 hours

Options for Background Work in Android

| Tool                   | When to Use                                            |
| ---------------------- | ------------------------------------------------------ |
| **`Handler`/`Thread`** | Short background work while app is active              |
| **`Service`**          | Long-running work, may run even with app in background |
| **`JobScheduler`**     | Scheduled jobs (API 21+)                               |
| **`WorkManager`**      | Reliable background tasks with constraints             |

## 👉 Why Use WorkManager?
WorkManager is the recommended solution by Google for background work that needs to be:

- Persistent (even after reboot)
- Scheduled (e.g. once every day)
- Deferred (run when on Wi-Fi, charging, etc.)

## 👉 WorkManager Key Components
- Worker – What task should run
- WorkRequest – How and when it should run
- WorkManager – Submits and manages the work

**Code Example:**
Upload Logs in Background

Add Dependencies
In build.gradle(:app):
```
implementation "androidx.work:work-runtime-ktx:2.9.0"
```

Create Your Worker
```
class UploadWorker(context: Context, workerParams: WorkerParameters)
: Worker(context, workerParams) {

    override fun doWork(): Result {
        // Simulate upload logic
        Log.d("UploadWorker", "Uploading logs...")
        
        // return success, failure or retry
        return Result.success()
    }
}
```

**Enqueue the Work**
For One-time task:
```
val uploadRequest = OneTimeWorkRequestBuilder<UploadWorker>().build()
WorkManager.getInstance(context).enqueue(uploadRequest)
```

For periodic task (e.g. every 6 hours):
```
val periodicRequest = PeriodicWorkRequestBuilder<UploadWorker>(
6, TimeUnit.HOURS
).build()

WorkManager.getInstance(context).enqueueUniquePeriodicWork(
"upload_logs",
ExistingPeriodicWorkPolicy.KEEP,
periodicRequest
)
```

Add to Manifest (Optional if using WorkManager directly)
If you use custom permissions/network, add them:
```
<uses-permission android:name="android.permission.INTERNET"/>
```

Add Constraints (e.g. run only on Wi-Fi + charging)
```
val constraints = Constraints.Builder()
.setRequiredNetworkType(NetworkType.UNMETERED) // Wi-Fi
.setRequiresCharging(true)
.build()

val work = OneTimeWorkRequestBuilder<UploadWorker>()
.setConstraints(constraints)
.build()

WorkManager.getInstance(context).enqueue(work)
```

**Summary**

| Concept               | What It Means                          |
| --------------------- | -------------------------------------- |
| `Worker`              | The background job logic               |
| `OneTimeWorkRequest`  | Runs once                              |
| `PeriodicWorkRequest` | Runs repeatedly (min 15 min interval)  |
| `Constraints`         | Control when it runs (Wi-Fi, charging) |
| `WorkManager`         | Scheduler that runs your workers       |

---

## 👉 Mini App: Jetpack Compose + WorkManager

- Shows a button
- Enqueues a task
- Logs the result in console

Goal:
- Tap a button in UI
- Enqueue a background Worker
- Show a Toast when it completes
- Logs output to Logcat

Add Dependency
```
implementation(libs.androidx.runtime.android)
implementation (libs.androidx.work.runtime.ktx)

implementation("androidx.activity:activity-compose:1.8.2")
implementation("androidx.compose.ui:ui:1.6.7")
implementation("androidx.compose.material3:material3:1.2.1")
implementation("androidx.compose.ui:ui-tooling-preview:1.6.7")
debugImplementation("androidx.compose.ui:ui-tooling:1.6.7")
```

```
kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14" // Match with Kotlin 1.9.24
    }
```

Create the Worker Class
UploadWorker

MainActivity with Jetpack Compose UI

Jetpack Compose Screen
Inside MainActivity.kt or separate WorkManagerDemo.kt:

**Output:**
What Happens When You Click the Button?
- A WorkManager task is scheduled to run after 3 seconds
- Log message appears in Logcat:

D/UploadWorker: Uploading logs in background...
Toast message says: "Work Enqueued"


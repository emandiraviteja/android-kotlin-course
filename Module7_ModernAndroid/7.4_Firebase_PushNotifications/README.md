# ✅  7.4 Firebase Integration

---

## 👉 What is FCM?

Firebase Cloud Messaging (FCM) is a free service by Google that allows you to:

- Send push notifications to Android, iOS, and web apps 
- Deliver messages in real time 
- Use either Firebase Console or server (API)

### ⚡ Push Notification Flow
```
FCM Server → Firebase → Device (Android App)
```

- Server sends message → Firebase → routes to device via token
- App receives it via a background service (FirebaseMessagingService)

Setup Firebase in Android Project

**Steps:**

- Go to Firebase Console 
- Create a new project (e.g., FCMDemo)
- Add Android app (com.example.fcmdemo)
- Download google-services.json and paste into:

```
app/google-services.json
```

Gradle Configuration
project-level build.gradle
```
classpath 'com.google.gms:google-services:4.4.1'
```

app-level build.gradle
```
plugins {
    id 'com.android.application'
    id 'com.google.gms.google-services' // add this
}

dependencies {
    implementation 'com.google.firebase:firebase-messaging:23.4.0'
}
```

Create Firebase Messaging Service
MyFirebaseMessagingService.kt

```
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Handle foreground and background messages
        remoteMessage.notification?.let {
            showNotification(it.title, it.body)
        }
    }

    private fun showNotification(title: String?, message: String?) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "fcm_channel_id"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, "FCM Notifications",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title ?: "FCM Message")
            .setContentText(message ?: "")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)

        notificationManager.notify(1, notificationBuilder.build())
    }

    override fun onNewToken(token: String) {
        Log.d("FCM_TOKEN", "New token: $token")
        // Send token to your server if needed
    }
}
```

Register the Service in `AndroidManifest.xml`
```
<service
    android:name=".MyFirebaseMessagingService"
    android:exported="false">
    <intent-filter>
        <action android:name="com.google.firebase.MESSAGING_EVENT" />
    </intent-filter>
</service>
```

Send Notification from Firebase Console

- Go to Firebase Console > Cloud Messaging
- Click Send your first message 
- Enter:
    Title: Hello
    Message: This is a test notification
- Target your app by Package Name
- Click Test

Show FCM Token in Log
MainActivity.kt

```
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val token = task.result
                    Log.d("FCM_TOKEN", "Token: $token")
                    Toast.makeText(this, "Token: $token", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
```





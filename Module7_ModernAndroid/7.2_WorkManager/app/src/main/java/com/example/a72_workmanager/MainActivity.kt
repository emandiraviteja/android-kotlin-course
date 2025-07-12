package com.example.a72_workmanager

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.a72_workmanager.ui.theme.MyComposeAppTheme
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    WorkManagerDemo()
                }
            }
        }
    }

    @Composable
    fun WorkManagerDemo() {
        val context = LocalContext.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                // Build the work request
                val workRequest = OneTimeWorkRequestBuilder<UploadWorker>()
                    .setInitialDelay(3, TimeUnit.SECONDS) // optional delay
                    .build()

                // Enqueue work
                WorkManager.getInstance(context).enqueue(workRequest)

                // Notify user
                Toast.makeText(context, "Work Enqueued", Toast.LENGTH_SHORT).show()
            }) {
                Text("Start Background Task")
            }
        }
    }
}
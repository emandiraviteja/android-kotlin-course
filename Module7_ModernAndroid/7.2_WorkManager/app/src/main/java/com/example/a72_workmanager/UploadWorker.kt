package com.example.a72_workmanager


/**Created by Raviteja Emandi on 12,July,2025 **/
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters

class UploadWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        Log.d("UploadWorker", "Uploading logs in background...")
        // Simulate a background task (e.g., upload logs)
        Thread.sleep(2000)

        // Optional: show toast (must run on main thread)
        // You cannot show Toast directly here — it's background only

        return Result.success()
    }
}

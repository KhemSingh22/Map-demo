package com.example.googlemap.utils
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.googlemap.R

class MyWork(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        createnotifi("Background Task", "This notification genrated by Work Manager")
        return Result.success()
    }

    private fun createnotifi(mess: String, mess1: String) {
        var notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel("101", "channel", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val notificationbuilder = NotificationCompat.Builder(applicationContext,"101")
            .setContentTitle(mess)
            .setContentText(mess1)
            .setSmallIcon(R.drawable.phone_call)
        notificationManager.notify(1,notificationbuilder.build())

    }
}
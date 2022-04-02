package com.app.ecommerceapp.firebase

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.app.ecommerceapp.R
import com.app.ecommerceapp.activity.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random

private const val TAG = "FirebaseService"
const val DESTINATION_KEY = "destination"

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class FirebaseService : FirebaseMessagingService() {

    private val notificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d(TAG, "onMessageReceived: ${message.data}")

        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(DESTINATION_KEY, message.data[DESTINATION_KEY])
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }

        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            FLAG_ONE_SHOT or FLAG_IMMUTABLE
        } else {
            FLAG_ONE_SHOT
        }

        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, flags)

        val notificationId = Random.nextInt()
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentText(message.notification?.body ?: "")
            .setContentTitle(message.notification?.title ?: "")
            .setSmallIcon(R.drawable.ic_cart_msg)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }

        notificationManager.notify(notificationId, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, IMPORTANCE_HIGH).apply {
            name = "App Notifications"
        }
        notificationManager.createNotificationChannel(channel)
    }

    private companion object {
        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
    }
}
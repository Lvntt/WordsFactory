package dev.lantt.wordsfactory.notifications

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.core.presentation.MainActivity
import dev.lantt.wordsfactory.core.presentation.util.hasNotificationPermission

class NotificationHelper(private val context: Context) {

    @SuppressLint("MissingPermission")
    fun showTrainingNotification(textTitle: String, textContent: String) {
        createTrainingNotificationChannel()

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val notificationBuilder = NotificationCompat.Builder(context, TRAINING_CHANNEL_ID)
            .setContentTitle(textTitle)
            .setContentText(textContent)
            .setSmallIcon(R.drawable.ic_training)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && !hasNotificationPermission(context)) {
            return
        }

        NotificationManagerCompat
            .from(context)
            .notify(TRAINING_NOTIFICATION_ID, notificationBuilder.build())
    }

    private fun createTrainingNotificationChannel() {
        val trainingChannel = NotificationChannel(
            TRAINING_CHANNEL_ID,
            TRAINING_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(trainingChannel)
    }

    private companion object {
        const val TRAINING_CHANNEL_ID = "training_channel_id"
        const val TRAINING_CHANNEL_NAME = "Training Channel"
        const val TRAINING_NOTIFICATION_ID = 1
    }

}
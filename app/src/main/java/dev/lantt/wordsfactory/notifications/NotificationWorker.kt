package dev.lantt.wordsfactory.notifications

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dev.lantt.wordsfactory.R
import dev.lantt.wordsfactory.core.domain.repository.SettingsManager
import java.util.Calendar

class NotificationWorker(
    params: WorkerParameters,
    private val context: Context,
    private val notificationHelper: NotificationHelper,
    private val settingsManager: SettingsManager
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val textTitle = context.getString(R.string.trainingNotificationTitle)
        val textContent = context.getString(R.string.trainingNotificationContent)

        if (!userPassedTrainingToday()) {
            notificationHelper.showTrainingNotification(textTitle, textContent)
        }
        return Result.success()
    }

    private fun userPassedTrainingToday(): Boolean {
        val lastTrainingTimeMillis = settingsManager.getLastTrainingTimeMillis()

        val calendar = Calendar.getInstance()
        with(calendar) {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        val startOfDayTimeMillis = calendar.timeInMillis

        return lastTrainingTimeMillis >= startOfDayTimeMillis
    }
}
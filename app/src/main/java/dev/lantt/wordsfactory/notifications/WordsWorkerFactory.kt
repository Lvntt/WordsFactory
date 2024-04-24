package dev.lantt.wordsfactory.notifications

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import dev.lantt.wordsfactory.core.domain.repository.SettingsManager

class WordsWorkerFactory(
    private val notificationHelper: NotificationHelper,
    private val settingsManager: SettingsManager
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when (workerClassName) {
            NotificationWorker::class.java.name -> NotificationWorker(workerParameters, appContext, notificationHelper, settingsManager)
            else -> null
        }
    }
}
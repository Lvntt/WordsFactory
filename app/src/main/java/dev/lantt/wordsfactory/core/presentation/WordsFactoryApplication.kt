package dev.lantt.wordsfactory.core.presentation

import android.app.Application
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dev.lantt.wordsfactory.di.provideAppModule
import dev.lantt.wordsfactory.di.provideDataModule
import dev.lantt.wordsfactory.di.provideDomainModule
import dev.lantt.wordsfactory.di.provideNetworkModule
import dev.lantt.wordsfactory.di.providePresentationModule
import dev.lantt.wordsfactory.notifications.NotificationWorker
import dev.lantt.wordsfactory.notifications.WordsWorkerFactory
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.Calendar
import java.util.concurrent.TimeUnit

class WordsFactoryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WordsFactoryApplication)
            modules(
                provideAppModule(),
                providePresentationModule(),
                provideDomainModule(),
                provideDataModule(),
                provideNetworkModule(),
            )
        }

        val wordsWorkerFactory by inject<WordsWorkerFactory>()

        val configuration = Configuration.Builder()
            .setWorkerFactory(wordsWorkerFactory)
            .build()
        WorkManager.initialize(this, configuration)

        val workManager = WorkManager.getInstance(this)
        val trainingNotificationDelay = calculateNotificationDelayMillis()

        val workRequest = PeriodicWorkRequestBuilder<NotificationWorker>(1, TimeUnit.DAYS)
            .setInitialDelay(trainingNotificationDelay, TimeUnit.MILLISECONDS)
            .build()

        workManager.enqueueUniquePeriodicWork(
            "notification_work",
            ExistingPeriodicWorkPolicy.REPLACE,
            workRequest
        )
    }

    private fun calculateNotificationDelayMillis(): Long {
        val calendar = Calendar.getInstance()
        val currentTimeMillis = calendar.timeInMillis

        with(calendar) {
            set(Calendar.HOUR_OF_DAY, 20)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        val targetTimeMillis = calendar.timeInMillis

        return targetTimeMillis - currentTimeMillis
    }
}
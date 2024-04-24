package dev.lantt.wordsfactory.di

import dev.lantt.wordsfactory.notifications.NotificationHelper
import dev.lantt.wordsfactory.notifications.WordsWorkerFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

fun provideAppModule(): Module = module {

    single { NotificationHelper(androidApplication().applicationContext) }

    single { WordsWorkerFactory(get(), get()) }

}
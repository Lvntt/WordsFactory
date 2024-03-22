package dev.lantt.wordsfactory.core.presentation

import android.app.Application
import dev.lantt.wordsfactory.di.providePresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WordsFactoryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WordsFactoryApplication)
            modules(
                providePresentationModule()
            )
        }
    }
}
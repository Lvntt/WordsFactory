package dev.lantt.wordsfactory.di

import dev.lantt.wordsfactory.core.Constants.BASE_URL
import dev.lantt.wordsfactory.core.Constants.CONNECT_TIMEOUT_SEC
import dev.lantt.wordsfactory.core.Constants.READ_TIMEOUT_SEC
import dev.lantt.wordsfactory.core.Constants.WRITE_TIMEOUT_SEC
import dev.lantt.wordsfactory.dictionary.data.network.DictionaryApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private fun provideLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

private fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor
): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT_SEC, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT_SEC, TimeUnit.SECONDS)
        .build()

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    baseUrl: String
): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

private fun provideDictionaryApiService(
    retrofit: Retrofit
): DictionaryApiService =
    retrofit.create(DictionaryApiService::class.java)

fun provideNetworkModule(): Module = module {

    single { provideLoggingInterceptor() }

    single { provideOkHttpClient(get()) }

    single { provideRetrofit(get(), BASE_URL) }

    single { provideDictionaryApiService(get()) }

}
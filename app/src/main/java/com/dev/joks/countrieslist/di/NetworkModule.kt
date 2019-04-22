package com.dev.joks.countrieslist.di

import android.content.Context
import com.dev.joks.countrieslist.BuildConfig
import com.dev.joks.countrieslist.repository.Repository
import com.dev.joks.countrieslist.repository.RepositoryImpl
import com.dev.joks.countrieslist.service.ApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit

private const val CACHE_SIZE = 10 * 1024 * 1024L
private const val CONNECTION_TIMEOUT = 20L
private const val READ_TIMEOUT = 60L

val networkModule = module {

    single { provideOkHttpClient(androidApplication()) }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
    single { RepositoryImpl(get()) as Repository }
}

private fun provideOkHttpClient(context: Context) =
    OkHttpClient.Builder()
        .cache(Cache(File(context.cacheDir, "http"), CACHE_SIZE))
        .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor {
            it.proceed(
                it.request().newBuilder()
                    .addHeader("User-Agent", System.getProperty("http.agent"))
                    .addHeader("Accept-Charset", "UTF-8")
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-type", "application/json")
                    .addHeader("Accept-Language", Locale.getDefault().language)
                    .build()
            )
        }
        .addInterceptor(HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        })
        .build()

private fun provideRetrofit(client: OkHttpClient) =
    Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.BASE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

private fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)
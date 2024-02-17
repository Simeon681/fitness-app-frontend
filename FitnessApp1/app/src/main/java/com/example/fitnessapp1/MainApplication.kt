package com.example.fitnessapp1

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.*
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.KoinApplication.Companion.init
import org.koin.core.context.startKoin

class MainApplication : Application() {

//    companion object {
//        lateinit var instance: MainApplication
//            private set
//
//    }
//
//    init {
//        instance = this
//    }
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}
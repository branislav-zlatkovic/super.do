package com.example.myapplication

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

class StoreDoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}
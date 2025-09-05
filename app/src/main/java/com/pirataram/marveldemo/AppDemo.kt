package com.pirataram.marveldemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppDemo : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}
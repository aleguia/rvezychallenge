package com.leguia.rvezy

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RVezyApplication: Application() {


    override fun onCreate() {
        super.onCreate()
    }
}
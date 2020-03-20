package com.example.adjoesampleapp

import android.app.Application
import android.util.Log

class SampleApplication : Application() {
    private lateinit var manager: DataManager

    override fun onCreate() {
        Log.d(TAG,  "onCreate")
        super.onCreate()
        manager = DataManager(applicationContext)
        manager.start()
    }
}
package com.example.adjoesampleapp

import android.app.Application

class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LogManager(applicationContext).log()
    }
}
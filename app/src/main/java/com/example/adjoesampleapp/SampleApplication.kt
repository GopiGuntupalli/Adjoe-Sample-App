package com.example.adjoesampleapp

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import android.util.Log
import java.lang.reflect.Method


private const val TAG = "Sample_App"

class SampleApplication : Application() {
    private lateinit var manager: DataManager

    override fun onCreate() {
        Log.d(TAG,  "onCreate, processName ${getProcessName()}")
        super.onCreate()
        manager = DataManager(applicationContext)
        manager.start()
    }

    companion object {
        val graphics3dLibrary = SomeNDKLibrary

        @SuppressLint("DiscouragedPrivateApi", "PrivateApi")
        fun getProcessName(): String? {
            return if (Build.VERSION.SDK_INT >= 28) Application.getProcessName() else { // Using the same technique as Application.getProcessName() for older devices
                // Using reflection since ActivityThread is an internal API
                try {
                    val activityThread = Class.forName("android.app.ActivityThread")
                    val getProcessName: Method = activityThread.getDeclaredMethod("currentProcessName")
                    (getProcessName.invoke(null) as String)
                } catch (e: Exception) {
                    return null
                }
            }
        }
    }
}
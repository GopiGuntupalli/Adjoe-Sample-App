package com.example.adjoesampleapp

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.preference.PreferenceManager
import android.util.Log
import java.lang.ref.WeakReference
import kotlin.concurrent.timer


class LogManager(con: Context) {
    private val context = WeakReference(con)
    init {
        context.let {
            val preferences = PreferenceManager.getDefaultSharedPreferences(it.get())
            val  editor = preferences.edit()
            editor.putInt(COUNTER, 0)
            editor.commit()
        }

    }
    fun log() {
        timer(period = 5000) {
            context.let {
                val preferences = PreferenceManager.getDefaultSharedPreferences(it.get())
                val count = preferences.getInt(COUNTER, 0)
                Log.d("Sample App", "Count:  $count")
                val editor = preferences.edit()
                editor.putInt(COUNTER, count+1)
                editor.commit()
            }
        }
    }

    companion object{
        private const val COUNTER = "counter"
    }
}
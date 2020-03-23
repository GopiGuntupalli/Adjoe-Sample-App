package com.example.adjoesampleapp

import android.util.Log

object SomeNDKLibrary {
    private const val TAG = "Sample_SomeNDKLibrary"

    init {
        Log.d(TAG, "<init> -- simulate loading static library built by NDK")
        Log.d(TAG, "processName ${SampleApplication.getProcessName()}")
    }
}
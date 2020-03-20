package com.example.adjoesampleapp

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.ref.WeakReference
import kotlin.concurrent.timer

class DataManager(con: Context) {
    private val contextRef = WeakReference(con)
    init {
        writeData(0)
        Log.d(TAG, "<init>")
    }

    fun start() {
        timer(period = 3000) {
            contextRef.get()?.let {context ->
                val fileInput = context.openFileInput(FILE_NAME)
                val inputStreamReader = InputStreamReader(fileInput)
                val bufferedReader = BufferedReader(inputStreamReader)
                val data: Int = bufferedReader.readLine().toInt()
                Log.d(TAG, "data: $data")

                writeData(data + 1)
            }
        }
    }

    private fun writeData(i: Int) {
        contextRef.get()?.let { context ->
            val fileOutput = context.openFileOutput(FILE_NAME, MODE_PRIVATE)
            val outputStreamWriter = OutputStreamWriter(fileOutput)
            outputStreamWriter.write(i.toString())
            outputStreamWriter.flush()
            outputStreamWriter.close()
        }
    }
}

private const val FILE_NAME = "some_data"

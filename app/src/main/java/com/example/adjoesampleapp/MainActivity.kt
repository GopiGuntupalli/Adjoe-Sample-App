package com.example.adjoesampleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.adjoe.sdk.Adjoe
import io.adjoe.sdk.AdjoeException
import io.adjoe.sdk.AdjoeInitialisationListener
import io.adjoe.sdk.AdjoeNotInitializedException
import kotlinx.android.synthetic.main.activity_main.*

const val TAG = "AdjoeSampleApp"

class MainActivity : AppCompatActivity() {
    companion object {
        private const val ADJOE_API_KEY = "90ae07658e27a4e1db494e46d3522049"
        private const val USER_ID = "232964276"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        show.setOnClickListener {
            val options: Adjoe.Options = Adjoe.Options().setUserId(USER_ID)
            Adjoe.init(this, ADJOE_API_KEY, options, object : AdjoeInitialisationListener {
                override fun onInitialisationFinished() {
                    // the adjoe SDK was initialized successfully
                    try {
                        val adjoeOfferwallIntent = Adjoe.getOfferwallIntent(this@MainActivity)
                        startActivity(adjoeOfferwallIntent)
                    } catch (notInitializedException: AdjoeNotInitializedException) {
                        // you have not initialized the adjoe SDK
                    } catch (exception: AdjoeException) {
                        //the offerwall cannot be displayed for some other reason
                    }
                }

                override fun onInitialisationError(exception: Exception?) {
                    // an error occurred while initializing the adjoe SDK. // note that exception might be null
                }
            })
        }
    }
}

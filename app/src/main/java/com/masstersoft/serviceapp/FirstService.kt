package com.masstersoft.serviceapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class FirstService : Service() {
    val LOG_TAG = "ServiceAPP"

    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG, "onCreate FirstService")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy FirstService")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(LOG_TAG, "onStartCommand FirstService")
        someWork()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d(LOG_TAG, "onBind FirstService")
        return null
    }

    fun someWork() {
        Log.d(LOG_TAG, "Doing some work FirstService")
    }
}

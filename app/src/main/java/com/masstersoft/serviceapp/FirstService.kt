package com.masstersoft.serviceapp

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

public class FirstService : Service() {
    val LOG_TAG = "ServiceAPP"
    var counter: Int = 0
    var startCommand: Int = 0

    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG, "onCreate thread = ${Thread.currentThread().id}")
        counter++
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(LOG_TAG, "onStartCommand counter = $counter thread = ${Thread.currentThread().id}")
        startCommand++
        //someWork(startCommand)
        someWork2(startCommand)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy thread = ${Thread.currentThread().id}")
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d(LOG_TAG, "onBind FirstService")
        return Binder()
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.d(LOG_TAG, "onRebind FirstService")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(LOG_TAG, "onUnBind FirstService");
        return super.onUnbind(intent)
    }

    fun someWork(count: Int) {
        Log.d(LOG_TAG, "Start doing some work FirstService command = $count")
        Thread.sleep(500)
        Log.d(LOG_TAG, "Stop doing some work FirstService command = $count")
    }

    fun someWork2(index: Int = 1) {
        Thread(object : Runnable {
            override fun run() {
                for (i in 1..5) {
                    Log.d(LOG_TAG, "Doing some work i = $i thread = ${Thread.currentThread().id}")
                    Thread.sleep(1500L / index)
                }
                stopSelf()
            }
        }).start()
    }
}

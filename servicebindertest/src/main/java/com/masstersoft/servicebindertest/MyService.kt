package com.masstersoft.servicebindertest

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.R.string.cancel
import android.os.Binder
import android.util.Log
import java.util.*


class MyService : Service() {

    val LOG_TAG = "myLogs"

    var binder = MyBinder()

    lateinit var timer: Timer
    var tTask: TimerTask? = null
    var interval: Long = 1000

    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG, "MyService onCreate")
        timer = Timer()
        schedule()
    }

    fun schedule() {
        if (tTask != null) tTask!!.cancel()
        if (interval > 0) {
            tTask = object : TimerTask() {
                override fun run() {
                    Log.d(LOG_TAG, "run")
                }
            }
            timer.schedule(tTask, 1000, interval)
        }
    }

    fun upInterval(gap: Long): Long {
        interval = interval + gap
        schedule()
        return interval
    }

    fun downInterval(gap: Long): Long {
        interval = interval - gap
        if (interval < 0) interval = 0
        schedule()
        return interval
    }

    override fun onBind(arg0: Intent): IBinder? {
        Log.d(LOG_TAG, "MyService onBind")
        return binder
    }

    inner class MyBinder : Binder() {
        val service: MyService
            get() = this@MyService
    }
}

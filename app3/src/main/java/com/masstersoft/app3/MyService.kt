package com.masstersoft.app3

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import javax.xml.datatype.DatatypeConstants.SECONDS



class MyService : Service() {

    val LOG_TAG = "myLogs"

    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG, "MyService onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "MyService onDestroy")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(LOG_TAG, "MyService onStartCommand")
        readFlags(flags)
        val mr = MyRun(startId)
        Thread(mr).start()
        return Service.START_NOT_STICKY
    }

    override fun onBind(arg0: Intent): IBinder? {
        return null
    }

    fun readFlags(flags: Int) {
        if (flags and Service.START_FLAG_REDELIVERY == Service.START_FLAG_REDELIVERY)
            Log.d(LOG_TAG, "START_FLAG_REDELIVERY")
        if (flags and Service.START_FLAG_RETRY == Service.START_FLAG_RETRY)
            Log.d(LOG_TAG, "START_FLAG_RETRY")
    }

    internal inner class MyRun(var startId: Int) : Runnable {

        init {
            Log.d(LOG_TAG, "MyRun#$startId create")
        }

        override fun run() {
            Log.d(LOG_TAG, "MyRun#$startId start")
            try {
                Thread.sleep(15000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            stop()
        }

        fun stop() {
            Log.d(
                LOG_TAG, "MyRun#" + startId + " end, stopSelfResult("
                        + startId + ") = " + stopSelfResult(startId)
            )
        }
    }
}

package com.masstersoft.serviceapp

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var bound = false
    val LOG_TAG = "ServiceAPP"
    val serviceConnection = getServiceConnection()
    lateinit var int: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
        int = Intent(this, FirstService::class.java)
    }

    private fun getServiceConnection() = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, binder: IBinder) {
            Log.d(LOG_TAG, "MainActivity onServiceConnected")
            bound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            Log.d(LOG_TAG, "MainActivity onServiceDisconnected")
            bound = false
        }
    }

    private fun setListeners() {
        btnStart.setOnClickListener {
            startService(Intent(this, FirstService::class.java))
        }

        btnStop.setOnClickListener {
            stopService(Intent(this, FirstService::class.java))
        }

        btnBind.setOnClickListener {
            bindService(int, serviceConnection, BIND_AUTO_CREATE)
        }

        btnUnBind.setOnClickListener {
            onUnbindService()
        }
    }

    private fun onUnbindService() {
        if (bound) {
            unbindService(serviceConnection)
            bound = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        onUnbindService()
    }
}

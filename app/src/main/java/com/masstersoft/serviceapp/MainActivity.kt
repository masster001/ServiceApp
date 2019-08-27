package com.masstersoft.serviceapp

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var bound = false
    val LOG_TAG = "FirstService"
    val serviceConnection = getServiceConnection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()

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

//        btnBind.setOnClickListener {
//            bindService(intent, serviceConnection, BIND_AUTO_CREATE)
//        }

        btnBind.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                bindService(intent, serviceConnection, BIND_AUTO_CREATE)
            }
        })

//        btnUnBind.setOnClickListener {
//            if (bound) {
//                unbindService(serviceConnection)
//                bound = false
//            }
//        }

        btnUnBind.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                if (!bound) return
                unbindService(serviceConnection)
                bound = false
            }
        })
    }
}

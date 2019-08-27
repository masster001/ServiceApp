package com.masstersoft.servicebindertest

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val LOG_TAG = "myLogs"

    var bound = false;
    var serviceConn = getServiceConnection()
    lateinit var int: Intent
    lateinit var myService: MyService
    var interval: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        int = Intent(this, MyService::class.java)
        setClickListeners()
    }

    fun setClickListeners() {
        btnStart.setOnClickListener { startService(int) }
        btnUp.setOnClickListener {
            if (bound) {
                interval = myService.upInterval(500)
                tvInterval.text = "interval = $interval"
            }
        }
        btnDown.setOnClickListener {
            if (bound) {
                interval = myService.downInterval(500)
                tvInterval.text = "interval = $interval"
            }
        }
    }

    fun getServiceConnection() = object : ServiceConnection {

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            Log.d(LOG_TAG, "MainActivity onServiceConnected");
            myService = (p1 as MyService.MyBinder).service
            bound = true;
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            Log.d(LOG_TAG, "MainActivity onServiceDisconnected")
            bound = false
        }
    }

    override fun onStart() {
        super.onStart()
        bindService(int, serviceConn, 0);
    }

    override fun onStop() {
        super.onStop()
        if (!bound) return
        unbindService(serviceConn)
        bound = false
    }


    /*
    * public void onClickStart(View v) {
    startService(intent);
  }

  public void onClickUp(View v) {
    if (!bound) return;
    interval = myService.upInterval(500);
    tvInterval.setText("interval = " + interval);
  }

  public void onClickDown(View v) {
    if (!bound) return;
    interval = myService.downInterval(500);
    tvInterval.setText("interval = " + interval);
  }*/
}

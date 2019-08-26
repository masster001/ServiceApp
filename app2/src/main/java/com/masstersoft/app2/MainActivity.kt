package com.masstersoft.app2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private fun setListeners(){
        btnStart.setOnClickListener {
            startService(Intent("com.masstersoft.serviceapp.FirstService")
                .setPackage("com.masstersoft"))
        }
    }
}

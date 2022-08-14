package com.gmail.vlaskorobogatov.astonhw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getFlags(view: View) {
        val intent = Intent(this, FlagsActivity::class.java).apply {}
        startActivity(intent)
    }

    fun getImage(view: View) {
        val intent = Intent(this, ImgActivity::class.java).apply {}
        startActivity(intent)
    }

    fun getClock(view: View) {
        val intent = Intent(this, ClockActivity::class.java).apply {}
        startActivity(intent)
    }
}
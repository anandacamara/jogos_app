package com.example.jogosapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.jogosapp.R
import com.example.jogosapp.databinding.ActivitySplashBinding

class SplashScreen : AppCompatActivity() {
    private lateinit var bind: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(bind.root)

        Handler(Looper.getMainLooper()).postDelayed({
            callLoginAcivity()
        }, 1000)

    }

    private fun callLoginAcivity(){
        startActivity(Intent(this, LoginActivity::class.java))
    }
}
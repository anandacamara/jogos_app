package com.example.jogosapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jogosapp.databinding.ActivitySplashBinding

class SplashScreen : AppCompatActivity() {
    private lateinit var bind: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(bind.root)
    }

    private fun callLoginAcivity(){
        //startActivity(Intent(this, LoginActivity::class.java))
    }
}
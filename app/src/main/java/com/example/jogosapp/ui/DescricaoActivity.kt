package com.example.jogosapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jogosapp.databinding.ActivityDescricaoBinding

class DescricaoActivity : AppCompatActivity() {
    private lateinit var bind: ActivityDescricaoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityDescricaoBinding.inflate(layoutInflater)
        setContentView(bind.root)


    }
}
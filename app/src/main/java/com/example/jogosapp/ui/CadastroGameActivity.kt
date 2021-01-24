package com.example.jogosapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jogosapp.databinding.ActivityCadastroGameBinding

class CadastroGameActivity : AppCompatActivity() {
    lateinit var bind: ActivityCadastroGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityCadastroGameBinding.inflate(layoutInflater)

        setContentView(bind.root)
    }
}
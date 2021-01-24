package com.example.jogosapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jogosapp.databinding.ActivityDescricaoBinding

class DescricaoActivity : AppCompatActivity() {
    private lateinit var bind: ActivityDescricaoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityDescricaoBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.toolbarDescricaoActivity.setNavigationOnClickListener { onBackPressed() }

        bind.buttonEditGame.setOnClickListener{
            startActivity(Intent(this, CadastroGameActivity::class.java))
        }
    }
}
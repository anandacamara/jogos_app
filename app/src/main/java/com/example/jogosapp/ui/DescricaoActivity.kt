package com.example.jogosapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.jogosapp.databinding.ActivityDescricaoBinding
import com.example.jogosapp.domain.Game

class DescricaoActivity : AppCompatActivity() {
    private lateinit var bind: ActivityDescricaoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityDescricaoBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.toolbarDescricaoActivity.setNavigationOnClickListener { onBackPressed() }

        val game = intent.getSerializableExtra("game") as Game

        bind.tvNameGameDescription.text = game.name
        bind.tvYearDescription.text = game.year
        bind.tvDescription.text = game.description

        Glide.with(this)
            .load(game.urlImage)
            .into(bind.ivGameImage)

        bind.buttonEditGame.setOnClickListener{
            val intent = Intent(this, CadastroGameActivity::class.java)
            intent.putExtra("game", game)
            startActivity(intent)
        }
    }
}
package com.example.jogosapp.ui

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.jogosapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var bind: ActivityHomeBinding
    lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(bind.root)

        gridLayoutManager = GridLayoutManager(this, 2)
        bind.recyclerViewHome.layoutManager = gridLayoutManager

        bind.floatingButtonGame.setOnClickListener { view ->
            startActivity(Intent(this, CadastroGameActivity::class.java))
        }
    }
}
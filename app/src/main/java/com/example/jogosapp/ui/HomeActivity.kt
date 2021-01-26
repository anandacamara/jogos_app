package com.example.jogosapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.example.jogosapp.databinding.ActivityHomeBinding
import com.example.jogosapp.domain.GameAdapter
import com.example.jogosapp.service.ServiceFirebaseDatabase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HomeActivity : AppCompatActivity(), GameAdapter.OnClickGame {
    private lateinit var bind: ActivityHomeBinding
    private lateinit var gameAdapter: GameAdapter
    private lateinit var gridLayoutManager: GridLayoutManager
    private var firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var reference: DatabaseReference = firebaseDatabase.getReference("games")

    private val viewModel by viewModels<HomeViewModel>{
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return HomeViewModel(reference) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(bind.root)

        viewModel.popListOfGames()

        firebaseDatabase = FirebaseDatabase.getInstance()

        gameAdapter = GameAdapter(this, this)
        gridLayoutManager = GridLayoutManager(this, 2)
        bind.recyclerViewHome.layoutManager = gridLayoutManager
        bind.recyclerViewHome.adapter = gameAdapter
        bind.recyclerViewHome.hasFixedSize()


        viewModel.listGames.observe(this) {
            gameAdapter.addGames(it)
        }

        bind.floatingButtonGame.setOnClickListener { view ->
            startActivity(Intent(this, CadastroGameActivity::class.java))
        }
    }

    override fun onClickGame(position: Int) {
        val game = gameAdapter.listGame[position]
        val intent = Intent(this, DescricaoActivity::class.java)
                intent.putExtra("game", game)

        startActivity(intent)
    }
}
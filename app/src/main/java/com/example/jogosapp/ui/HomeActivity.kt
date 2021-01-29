package com.example.jogosapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.example.jogosapp.databinding.ActivityHomeBinding
import com.example.jogosapp.domain.Game
import com.example.jogosapp.domain.GameAdapter
import com.example.jogosapp.service.databaseReference
import java.util.*
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity(), GameAdapter.OnClickGame {
    private lateinit var bind: ActivityHomeBinding
    private lateinit var gameAdapter: GameAdapter
    private lateinit var gridLayoutManager: GridLayoutManager
    private var listAllGames = ArrayList<Game>()

    private val viewModel by viewModels<HomeViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return HomeViewModel(databaseReference) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(bind.root)

        viewModel.popListOfGames()

        gameAdapter = GameAdapter(this, this)
        gridLayoutManager = GridLayoutManager(this, 2)
        bind.recyclerViewHome.layoutManager = gridLayoutManager
        bind.recyclerViewHome.adapter = gameAdapter
        bind.recyclerViewHome.hasFixedSize()


        viewModel.listGames.observe(this) {
            gameAdapter.addGames(it)
            val list = arrayListOf<Game>()
            list.addAll(it)
            listAllGames = list
        }

        bind.floatingButtonGame.setOnClickListener {
            startActivity(Intent(this, CadastroGameActivity::class.java))
        }

        searchGame()
    }

    override fun onClickGame(position: Int) {
        val game = gameAdapter.listGame[position]
        val intent = Intent(this, DescricaoActivity::class.java)
        intent.putExtra("game", game)

        startActivity(intent)
    }

    private fun searchGame() {
        val searchView = bind.searchview

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isNotEmpty()) {
                    gameAdapter.listGame.clear()
                    Log.i("newtext", newText)
                    Log.i("list", this@HomeActivity.listAllGames.toString())

                    val list = ArrayList<Game>()
                    this@HomeActivity.listAllGames.forEach {
                        if (it.name.toLowerCase(Locale.getDefault()).contains(
                                newText.toLowerCase(
                                    Locale.getDefault()
                                )
                            )
                        ) list.add(it)
                    }
                    gameAdapter.listGame = list
                    gameAdapter.notifyDataSetChanged()
                } else {
                    gameAdapter.listGame.clear()
                    gameAdapter.listGame = this@HomeActivity.listAllGames
                    gameAdapter.notifyDataSetChanged()
                }
                return true
            }
        })
    }
}

package com.example.jogosapp.domain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jogosapp.R
import com.example.jogosapp.ui.HomeActivity

class GameAdapter (val listener: OnClickGame, val context: HomeActivity): RecyclerView.Adapter<GameAdapter.GameViewHolder>() {
    var listGame = ArrayList<Game>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GameViewHolder(view)
    }

    override fun getItemCount(): Int = listGame.size

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val Game = listGame[position]
        holder.name.text = Game.name
        holder.year.text = Game.year

        Glide.with(context)
            .load(Game.urlImage)
            .into(holder.imagem)
    }

    fun addGames(games: List<Game>){
        listGame.clear()
        listGame.addAll(games)
        notifyDataSetChanged()
    }

    interface OnClickGame{
        fun onClickGame(position: Int)
    }

    inner class GameViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener{
        val name: TextView = view.findViewById(R.id.game_name_home)
        val year: TextView = view.findViewById(R.id.game_year_home)
        val imagem: ImageView = view.findViewById(R.id.img_game_home)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.onClickGame(position)
            }
        }
    }
}
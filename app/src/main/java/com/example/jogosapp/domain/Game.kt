package com.example.jogosapp.domain

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class Game(val name: String, val year: String, val description: String, val urlImage: String): Serializable, Comparable<Game>{

    override fun compareTo(other: Game): Int {
        return name.compareTo(other.name)
    }
}
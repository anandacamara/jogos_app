package com.example.jogosapp.domain

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Game(val name: String, val year: String, val description: String, val urlImage: String)
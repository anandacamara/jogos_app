package com.example.jogosapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.jogosapp.R
import com.example.jogosapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var bind: ActivityLoginBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bind.root)

        navController = findNavController(R.id.fragment_login_cadastro)
    }
}
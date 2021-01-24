package com.example.jogosapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.jogosapp.R
import com.example.jogosapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var bind: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bind = FragmentLoginBinding.inflate(layoutInflater)
        bind.tvCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_cadastroFragment)
        }

        bind.buttonLogin.setOnClickListener {
            startActivity(Intent(this.context, HomeActivity::class.java))
        }

        return bind.root
    }
}
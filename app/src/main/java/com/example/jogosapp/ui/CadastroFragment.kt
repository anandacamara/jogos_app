package com.example.jogosapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jogosapp.databinding.FragmentCadastroBinding

class CadastroFragment : Fragment() {
    private lateinit var bind: FragmentCadastroBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentCadastroBinding.inflate(layoutInflater)
        bind.buttonCadastro.setOnClickListener {
            startActivity(Intent(this.context, HomeActivity::class.java))
        }

        return bind.root
    }
}
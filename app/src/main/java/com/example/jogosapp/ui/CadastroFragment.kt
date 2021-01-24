package com.example.jogosapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jogosapp.R
import kotlinx.android.synthetic.main.fragment_cadastro.view.*

class CadastroFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_cadastro, container, false)

        view.button_cadastro.setOnClickListener {
         //   startActivity(Intent(this.context, HomeActivity::class.java))
        }
        return view
    }
}
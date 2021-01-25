package com.example.jogosapp.ui

import android.app.Activity
import android.content.Intent
import android.hardware.Camera
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.example.jogosapp.databinding.ActivityCadastroGameBinding
import com.example.jogosapp.domain.Game
import com.example.jogosapp.service.ServiceFirebaseDatabase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class CadastroGameActivity : AppCompatActivity() {
    lateinit var bind: ActivityCadastroGameBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var serviceDataBase: ServiceFirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityCadastroGameBinding.inflate(layoutInflater)
        setContentView(bind.root)

        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("games")
        serviceDataBase = ServiceFirebaseDatabase(reference)

        bind.buttonAddImage.setOnClickListener {
        }
    }

    fun getGame(url: String): Game {
        return Game(bind.etNomeGame.text.toString(), bind.etYearGame.text.toString(),
            bind.etDescriptionGame.text.toString(), url)
    }

    private fun dispatchTakePictureIntent() {
        Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.INTERNAL_CONTENT_URI).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), 1)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                val imagemSelecionada: Uri? = data?.data
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
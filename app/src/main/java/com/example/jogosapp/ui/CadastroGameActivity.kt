package com.example.jogosapp.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.jogosapp.databinding.ActivityCadastroGameBinding
import com.example.jogosapp.domain.Game
import com.example.jogosapp.service.ServiceFirebaseDatabase
import com.example.jogosapp.service.ServiceFirebaseStorage
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage


class CadastroGameActivity : AppCompatActivity() {
    lateinit var bind: ActivityCadastroGameBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var serviceDataBase: ServiceFirebaseDatabase
    private lateinit var serviceStorage: ServiceFirebaseStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityCadastroGameBinding.inflate(layoutInflater)
        setContentView(bind.root)

        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        reference = firebaseDatabase.getReference("games")

        serviceDataBase = ServiceFirebaseDatabase(reference)
        serviceStorage = ServiceFirebaseStorage(firebaseStorage)

        bind.buttonAddImage.setOnClickListener {
            dispatchTakePictureIntent()
        }

        bind.buttonSaveGame.setOnClickListener {
            val url = serviceStorage.urlImage
            if (url.isNotEmpty()){
                Log.i("TAG", getGame(url).toString())
                serviceDataBase.addGameInDatabase("1", getGame(url))
            }
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
                takePictureIntent.type = "image/*";
                startActivityForResult(Intent.createChooser(takePictureIntent, "Selecione uma imagem"), 1)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                val imagemSelecionada: Uri? = data?.data
                //val filePathColumn = {MediaStore.Images.Media.DATA}
                val url = "image${imagemSelecionada?.path
                        ?.substring(imagemSelecionada.path!!.lastIndexOf("/"))}.jpeg"
                Log.i("------------------",
                url!!)
                serviceStorage.uploadPhotoGame(imagemSelecionada, url)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}
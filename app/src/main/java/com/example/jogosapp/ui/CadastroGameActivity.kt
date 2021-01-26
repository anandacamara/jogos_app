package com.example.jogosapp.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.jogosapp.databinding.ActivityCadastroGameBinding
import com.example.jogosapp.domain.Game
import com.example.jogosapp.service.ServiceFirebaseDatabase
import com.example.jogosapp.service.ServiceFirebaseStorage
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage


class CadastroGameActivity : AppCompatActivity() {
    private lateinit var bind: ActivityCadastroGameBinding
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

        val obj = intent.getSerializableExtra("game")
        if (obj != null){
            val game = obj as Game
            addImageInButton(game.urlImage)
            bind.etNomeGame.setText(game.name)
            bind.etYearGame.setText(game.year)
            bind.etDescriptionGame.setText(game.description)
        }

        bind.buttonAddImage.setOnClickListener {
            dispatchTakePictureIntent()
        }

        bind.buttonSaveGame.setOnClickListener {
            val url = serviceStorage.urlImage
            if (url.isNotEmpty()) {
                Log.i("TAG", getGame(url).toString())
                val game = getGame(url)
                serviceDataBase.addGameInDatabase(game.name, game)
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }
    }

    private fun getGame(url: String): Game {
        return Game(
            bind.etNomeGame.text.toString(), bind.etYearGame.text.toString(),
            bind.etDescriptionGame.text.toString(), url
        )
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun dispatchTakePictureIntent() {
        Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.INTERNAL_CONTENT_URI
        ).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                takePictureIntent.type = "image/*"
                startActivityForResult(Intent.createChooser(takePictureIntent,
                        "Selecione uma imagem"), 1)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                val imagemSelecionada: Uri? = data?.data
                serviceStorage.uploadPhotoGame(imagemSelecionada)
                this.addImageInButton(imagemSelecionada)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun addImageInButton(uri: Uri?) {
        Glide.with(this)
            .load(uri)
            .into(bind.ivImgCamera)

        bind.ivImgCamera.visibility = ImageView.VISIBLE
        bind.ivIcCamera.visibility = ImageView.GONE
    }

    private fun addImageInButton(url: String) {
        Glide.with(this)
            .load(url)
            .into(bind.ivImgCamera)

        bind.ivImgCamera.visibility = ImageView.VISIBLE
        bind.ivIcCamera.visibility = ImageView.GONE
    }
}
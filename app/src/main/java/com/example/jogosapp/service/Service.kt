package com.example.jogosapp.service

import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.bumptech.glide.annotation.GlideExtension
import com.example.jogosapp.domain.Game
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import java.io.File

private val firebaseDatabase = FirebaseDatabase.getInstance()
private val databaseReference = firebaseDatabase.getReference("games")
val serviceDB = ServiceFirebaseDatabase(databaseReference)

class ServiceFirebaseStorage(private val firebaseStorage: FirebaseStorage) {
    var urlImage = ""

    fun uploadPhotoGame(uriFile: Uri?) {

        var file = Uri.fromFile(File(uriFile?.path))
        val ref = firebaseStorage.reference.child(file.lastPathSegment!!)
        val taskUpdate = ref.putFile(uriFile!!)
        taskUpdate.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            ref.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                urlImage = task.result.toString()
            } else {
                throw Exception()
            }
        }
    }
}

class ServiceFirebaseDatabase(private val reference: DatabaseReference) {

    fun addGameInDatabase(name: String, game: Game) {
        reference.child(name).setValue(game)
    }

    fun updateGameInDatabase(id: String, game: Game) {
        reference.child("games").child(id).child("name").setValue(game.name)
        reference.child("games").child(id).child("urlImage").setValue(game.urlImage)
        reference.child("games").child(id).child("description").setValue(game.description)
        reference.child("games").child(id).child("year").setValue(game.year)
    }
}
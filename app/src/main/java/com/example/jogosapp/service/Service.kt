package com.example.jogosapp.service

import android.net.Uri
import com.example.jogosapp.domain.Game
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import java.io.File

val firebaseDatabase = FirebaseDatabase.getInstance()
val databaseReference = firebaseDatabase.getReference("games")
val serviceDB = ServiceFirebaseDatabase(databaseReference)
val firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
val serviceStorage: ServiceFirebaseStorage = ServiceFirebaseStorage(firebaseStorage)

class ServiceFirebaseStorage(private val firebaseStorage: FirebaseStorage) {
    var urlImage = ""

    fun uploadPhotoGame(uriFile: Uri?) {

        val file = Uri.fromFile(File(uriFile?.path!!))
        val ref = firebaseStorage.reference.child(file.lastPathSegment!!)
        val taskUpdate = ref.putFile(uriFile)
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
}
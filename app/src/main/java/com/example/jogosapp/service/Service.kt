package com.example.jogosapp.service

import android.net.Uri
import com.example.jogosapp.domain.Game
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import java.io.File

class ServiceFirebaseStorage(private val firebaseStorage: FirebaseStorage) {
    var urlImage = ""

    fun uploadPhotoGame(uriFile: Uri?, fileName: String?) {

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

//        task.addOnProgressListener { (bytesTransferred, totalByteCount) ->
//            val progress = (100.0 * bytesTransferred) / totalByteCount
//
//        }.addOnPausedListener {
//
//        }
    }

//    private fun generateUrlDownload(
//        reference: StorageReference,
//        task: StorageTask<UploadTask.TaskSnapshot>,
//        success: (uri: Uri) -> Unit,
//        error: (Exception?) -> Unit
//    ) {
//        task.continueWithTask { taskExecuted ->
//            if(taskExecuted.isSuccessful) {
//                reference.downloadUrl
//            } else {
//                taskExecuted.exception?.let {
//                    throw it
//                }
//            }
//        }.addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                task.result?.let(success) ?: run {
//                    error(Throwable("Unknown Error"))
//                }
//            } else {
//                error(Throwable("Unknown Error!"))
//            }
//        }.addOnFailureListener(error)
//    }
}

class ServiceFirebaseDatabase(private val reference: DatabaseReference) {

    fun addGameInDatabase(id: String, game: Game) {
        reference.child("games").child(id).setValue(game)
    }

    fun updateGameInDatabase(id: String, game: Game) {
        reference.child("games").child(id).child("name").setValue(game.name)
        reference.child("games").child(id).child("urlImage").setValue(game.urlImage)
        reference.child("games").child(id).child("description").setValue(game.description)
        reference.child("games").child(id).child("year").setValue(game.year)
    }
}
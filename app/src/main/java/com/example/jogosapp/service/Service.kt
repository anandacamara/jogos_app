package com.example.jogosapp.service

import android.net.Uri
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask

val firebaseDouble: FirebaseDatabase = FirebaseDatabase.getInstance()
val firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
val storageRef: StorageReference = firebaseStorage.reference

class ServiceFirebaseStorage(private val firebaseStorage: FirebaseStorage){

    fun uploadPhotoGame(uriFile: Uri, fileName: String, success: (uri: Uri) -> Unit,
                        error: (Exception?) -> Unit) {
        val ref = firebaseStorage.getReference(fileName)
        val task = ref.putFile(uriFile)
        this.generateUrlDownload(ref, task, success, error)
    }

    private fun generateUrlDownload(
        reference: StorageReference,
        task: StorageTask<UploadTask.TaskSnapshot>,
        success: (uri: Uri) -> Unit,
        error: (Exception?) -> Unit
    ) {
        task.continueWithTask { taskExecuted ->
            if(taskExecuted.isSuccessful) {
                reference.downloadUrl
            } else {
                taskExecuted.exception?.let {
                    throw it
                }
            }
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                task.result?.let(success) ?: run {
                    error(Throwable("Unknown Error"))
                }
            } else {
                error(Throwable("Unknown Error!"))
            }
        }.addOnFailureListener(error)
    }
}
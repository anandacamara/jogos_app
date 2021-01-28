package com.example.jogosapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jogosapp.domain.Game
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch

class HomeViewModel(private val reference: DatabaseReference): ViewModel() {
    val listGames = MutableLiveData<List<Game>>()

    fun popListOfGames(){
        viewModelScope.launch {
            val list = ArrayList<Game>()
            reference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    dataSnapshot.children.forEach {
                        val result = it.value as HashMap<String, String>
                        val gameFirebase = Game(
                            result["name"]!!, result["year"]!!,
                            result["description"]!!, result["urlImage"]!!
                        )
                        list.add(gameFirebase)
                        listGames.value = list
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Erro", error.toString())
                }
            })
        }
    }
}
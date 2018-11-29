package io.krice.myapplication

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class NewsLiveData: MutableLiveData<MutableList<News>>() {
    val newsList: MutableList<News> = mutableListOf()

    override fun onActive() {
        val database = FirebaseDatabase.getInstance()


        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                newsList.clear()
                Log.d("news", dataSnapshot.toString())
                dataSnapshot.children.mapNotNullTo(newsList) { it.getValue<News>(News::class.java) }
                postValue(newsList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadPost:onCancelled ${databaseError.toException()}")
            }
        }

        database.reference.child("news").addValueEventListener(listener)
    }

    override fun onInactive() {
    }
}
package com.fernando.mylauncher

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
   val  mapper =  ObjectMapper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvItens.layoutManager = GridLayoutManager(applicationContext, 4)
        val itemList = arrayListOf<Item>()
        val adapter = ItemAdapter(applicationContext , itemList)
        rvItens.adapter = adapter

        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("launcher")
        docRef.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            querySnapshot?.let { querySnapshot ->
                querySnapshot.documents.let {documents ->
                    itemList.removeAll(itemList)
                    documents.forEach {
                        Log.d(MainActivity::class.java.name , "DocumentSnapshot data: " + it.data)
                        itemList.add(mapper.convertValue(it.data, Item::class.java))
                        adapter.notifyDataSetChanged()
                    }
                }


            }
        }
    }
}

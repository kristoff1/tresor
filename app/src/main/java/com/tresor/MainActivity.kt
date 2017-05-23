package com.tresor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.*
import java.util.HashMap

class MainActivity : AppCompatActivity() {

    private lateinit var et_date: EditText
    private lateinit var et_nominal: EditText
    private lateinit var et_info: EditText
    private lateinit var tv_date: TextView
    private lateinit var tv_nominal: TextView
    private lateinit var tv_info: TextView
    private lateinit var myRef: DatabaseReference
    private val USER = "Atin"

    private lateinit var date : String
    private lateinit var nominal : String
    private lateinit var info : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRef = FirebaseDatabase.getInstance().getReference()

        et_date = findViewById(R.id.et_date) as EditText
        et_nominal = findViewById(R.id.et_nominal) as EditText
        et_info = findViewById(R.id.et_info) as EditText
        tv_date = findViewById(R.id.tv_date) as TextView
        tv_nominal = findViewById(R.id.tv_nominal) as TextView
        tv_info = findViewById(R.id.tv_info) as TextView

        var send = findViewById(R.id.send_to_firebase) as Button



        send.setOnClickListener { writeNewPost() }
    }

    private fun writeNewPost() {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously

        val date = et_date.text.toString()
        val nominal = et_nominal.text.toString()
        val info = et_info.text.toString()
        val key = myRef.child(USER).push().getKey()

        val post = Post(date, nominal, info)
        val postValues = post.toMap()

        val childUpdates = HashMap<String, Any>()
        childUpdates.put("/$USER/$", postValues)

        myRef.child(USER).push().setValue(postValues)
    }

//    private fun receiveFromFirebase() {
//        myRef.addValueEventListener(object : ValueEventListener {
//            override fun onCancelled(p0: DatabaseError?) {
//                output.text = "Error"
//            }
//
//            override fun onDataChange(p0: DataSnapshot?) {
//                output.text = p0?.getValue(String::class.java) ?: "Error"
//            }
//
//        }
//        )
//    }





}


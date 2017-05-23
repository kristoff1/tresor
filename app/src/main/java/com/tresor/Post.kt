package com.tresor

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.HashMap

/**
 * @author atin on 5/20/2017.
 */
class Post(val date: String, val nominal: String, val info: String) {



    fun toMap(): Map<String, Any> {
        val result = HashMap<String, Any>()


        result.put("date", date)
        result.put("nominal", nominal)
        result.put("info", info)

        return result
    }



}

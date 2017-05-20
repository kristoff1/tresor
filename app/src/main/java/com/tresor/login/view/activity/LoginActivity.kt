package com.tresor.login.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.tresor.R

/**
 * @author sebastianuskh on 5/20/17.
 */

class LoginActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById(R.id.login_button) as Button
        loginButton.setOnClickListener {
            val email = (findViewById(R.id.email_edit_text) as EditText).text.toString()
            val password = (findViewById(R.id.password_edit_text) as EditText).text.toString()
        }
    }
}
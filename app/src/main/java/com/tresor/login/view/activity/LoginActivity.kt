package com.tresor.login.view.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.tresor.R
import com.tresor.base.BaseActivity
import com.tresor.login.di.component.DaggerLoginComponent
import com.tresor.login.view.presenter.LoginPresenter
import javax.inject.Inject

/**
 * @author sebastianuskh on 5/20/17.
 */

class LoginActivity: BaseActivity(){

    val component by lazy {
        DaggerLoginComponent
                .builder()
                .appComponent(getAppComponent())
                .build()}

    @Inject lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        component.inject(this)

        val loginButton = findViewById(R.id.login_button) as Button
        loginButton.setOnClickListener {
            val email = (findViewById(R.id.email_edit_text) as EditText).text.toString()
            val password = (findViewById(R.id.password_edit_text) as EditText).text.toString()
            presenter.login(email, password)
        }
    }
}
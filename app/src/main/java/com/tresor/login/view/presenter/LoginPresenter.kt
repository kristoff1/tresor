package com.tresor.login.view.presenter

import com.tresor.base.view.BasePresenter
import com.tresor.login.view.activity.LoginView

/**
 * @author sebastianuskh on 5/20/17.
 */

abstract class LoginPresenter : BasePresenter<LoginView>() {
    abstract fun login(email: String, password: String)
}
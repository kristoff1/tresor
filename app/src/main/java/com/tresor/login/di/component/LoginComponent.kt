package com.tresor.login.di.component

import com.tresor.base.di.component.AppComponent
import com.tresor.login.di.module.LoginModule
import com.tresor.login.di.scope.LoginScope
import com.tresor.login.view.activity.LoginActivity
import dagger.Component

/**
 * @author sebastianuskh on 5/20/17.
 */
@LoginScope
@Component(modules = arrayOf(LoginModule::class), dependencies = arrayOf(AppComponent::class))
interface LoginComponent {
    fun inject(loginActivity: LoginActivity)
}

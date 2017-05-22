package com.tresor.login.di.module

import com.tresor.login.data.repository.UserDataRepository
import com.tresor.login.data.repository.datasource.UserDataFactory
import com.tresor.login.di.scope.LoginScope
import com.tresor.login.domain.interactor.Login
import com.tresor.login.domain.repository.UserRepository
import com.tresor.login.view.presenter.LoginPresenter
import com.tresor.login.view.presenter.LoginPresenterImpl
import dagger.Module
import dagger.Provides

/**
 * @author sebastianuskh on 5/20/17.
 */
@Module
class LoginModule {

    @LoginScope
    @Provides
    fun providePresenter(login: Login): LoginPresenter = LoginPresenterImpl(login)

    @LoginScope
    @Provides
    fun provideUserRepository(userDataFactory: UserDataFactory): UserRepository = UserDataRepository(userDataFactory)

}
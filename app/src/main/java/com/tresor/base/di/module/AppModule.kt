package com.tresor.base.di.module

import android.content.Context
import com.tresor.base.MainApplication
import com.tresor.base.di.qualifier.ApplicationContext
import com.tresor.base.session.SessionHandler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author sebastianuskh on 5/20/17.
 */
@Module
class AppModule(val app: MainApplication){

    @Singleton
    @Provides
    @ApplicationContext
    fun provideApplicationContext(): Context {
        return app
    }

    @Singleton
    @Provides
    fun provideSessionHandler(): SessionHandler {
        return SessionHandler()
    }

}
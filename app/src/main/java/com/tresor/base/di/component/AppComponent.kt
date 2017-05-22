package com.tresor.base.di.component

import android.app.Activity
import com.tresor.base.di.module.AppModule
import com.tresor.base.domain.PostExecutionThread
import com.tresor.base.domain.ThreadExecutor
import com.tresor.base.navigator.Navigator
import dagger.Component
import javax.inject.Singleton


/**
 * @author sebastianuskh on 5/20/17.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent{
    fun inject (activity: Activity)

    fun getNavigator(): Navigator

    fun getThreadExecutor(): ThreadExecutor

    fun getPostExecutionThread(): PostExecutionThread
}
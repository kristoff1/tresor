package com.tresor.base.di.component

import android.app.Activity
import com.tresor.base.di.module.ActivityModule
import com.tresor.base.di.module.AppModule
import dagger.Component
import javax.inject.Singleton


/**
 * @author sebastianuskh on 5/20/17.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, ActivityModule::class))
interface AppComponent{
    fun inject (activity: Activity)
}
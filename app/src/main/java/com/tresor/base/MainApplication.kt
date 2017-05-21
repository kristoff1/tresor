package com.tresor.base

import android.app.Application
import com.tresor.base.di.component.AppComponent
import com.tresor.base.di.component.DaggerAppComponent
import com.tresor.base.di.module.ActivityModule
import com.tresor.base.di.module.AppModule

/**
 * @author sebastianuskh on 5/20/17.
 */

class MainApplication: Application(){

    val component by lazy { DaggerAppComponent
            .builder()
            .appModule(AppModule(this)) }

    override fun onCreate() {
        super.onCreate()
    }

    fun getAppComponent(activityModule: ActivityModule): AppComponent =
            component.activityModule(activityModule).build()
}

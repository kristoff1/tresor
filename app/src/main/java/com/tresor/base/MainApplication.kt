package com.tresor.base

import android.app.Application
import com.tresor.base.di.component.AppComponent
import com.tresor.base.di.component.DaggerAppComponent
import com.tresor.base.di.module.AppModule

/**
 * @author sebastianuskh on 5/20/17.
 */

class MainApplication: Application(){

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }
}

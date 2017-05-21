package com.tresor.base

import android.support.v7.app.AppCompatActivity
import com.tresor.base.di.component.AppComponent
import com.tresor.base.di.module.ActivityModule

/**
 * @author sebastianuskh on 5/21/17.
 */

abstract class BaseActivity : AppCompatActivity(){
    fun getAppComponent(): AppComponent{
        if (application is MainApplication)
            return (application as MainApplication).getAppComponent(ActivityModule(this))
        else
            throw RuntimeException("Application is not MainApplication")
    }
}
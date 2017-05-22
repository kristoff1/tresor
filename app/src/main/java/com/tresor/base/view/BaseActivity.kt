package com.tresor.base.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tresor.base.MainApplication
import com.tresor.base.di.component.AppComponent
import com.tresor.base.di.module.ActivityModule
import com.tresor.base.navigator.Navigator
import javax.inject.Inject

/**
 * @author sebastianuskh on 5/21/17.
 */

abstract class BaseActivity : AppCompatActivity() {

    @Inject lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAppComponent().inject(this)
    }

    fun getAppComponent(): AppComponent {
        if (application is MainApplication)
            return (application as MainApplication).getAppComponent()
        else
            throw RuntimeException("Application is not MainApplication")
    }

    fun getActivityModule(): ActivityModule {
        return ActivityModule(this)
    }
}
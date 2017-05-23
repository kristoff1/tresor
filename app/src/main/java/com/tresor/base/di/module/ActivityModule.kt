package com.tresor.base.di.module

import android.app.Activity
import android.content.Context
import com.tresor.base.di.qualifier.ActivityQualifier
import com.tresor.base.di.qualifier.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * @author sebastianuskh on 5/20/17.
 */
@Module
class ActivityModule (private val activity: Activity) {

    @ActivityScope
    @Provides
    @ActivityQualifier
    fun provideActivityContext() : Context {
        return activity
    }

}
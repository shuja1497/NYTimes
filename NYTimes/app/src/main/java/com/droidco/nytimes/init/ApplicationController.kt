package com.droidco.nytimes.init

import android.app.Application
import com.droidco.nytimes.BuildConfig
import com.droidco.nytimes.di.component.ApplicationComponent
import com.droidco.nytimes.di.component.DaggerApplicationComponent
import timber.log.Timber


class ApplicationController : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        applicationComponent = DaggerApplicationComponent.create()

    }

    companion object {
        private lateinit var applicationComponent: ApplicationComponent

        lateinit var instance: ApplicationController
            private set

        fun getAppComponent() = applicationComponent
    }
}
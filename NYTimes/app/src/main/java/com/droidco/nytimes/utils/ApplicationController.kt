package com.droidco.nytimes.utils

import android.app.Application
import com.droidco.nytimes.BuildConfig
import timber.log.Timber


class ApplicationController : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }

    companion object {
        lateinit var instance: ApplicationController
            private set
    }
}
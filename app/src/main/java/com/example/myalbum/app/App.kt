package com.example.myalbum.app

import android.content.Context
import com.facebook.stetho.Stetho
import com.example.myalbum.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/*
* Custom Application class.
* */
class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        appContext = applicationContext
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this@App)
    }

    companion object {
        lateinit  var appContext: Context
    }
}
package com.nicholasworkshop.moovuptest

import android.app.Application
import com.nicholasworkshop.moovuptest.api.ApiModule

class MainApplication : Application() {

    lateinit var component: MainComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerMainComponent.builder()
                .apiModule(ApiModule())
                .mainModule(MainModule(this))
                .build()
    }
}
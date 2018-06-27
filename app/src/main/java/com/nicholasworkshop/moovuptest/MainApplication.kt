package com.nicholasworkshop.moovuptest

import android.app.Application

class MainApplication: Application() {

    lateinit var component: MainComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerMainComponent.builder()
                .build()
    }
}
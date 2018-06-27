package com.nicholasworkshop.moovuptest

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule(
        private val application: Application) {

    @Provides
    @Singleton
    fun context(): Context {
        return application
    }

    @Provides
    @Singleton
    fun application(): Application {
        return application
    }
}
package com.nicholasworkshop.moovuptest

import com.nicholasworkshop.moovuptest.api.ApiModule
import com.nicholasworkshop.moovuptest.api.FriendService
import com.nicholasworkshop.moovuptest.model.DatabaseModule
import com.nicholasworkshop.moovuptest.model.FriendDao
import com.nicholasworkshop.moovuptest.model.FriendDatabase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        MainModule::class,
        DatabaseModule::class,
        ApiModule::class))
interface MainComponent {

    fun friendService(): FriendService

    fun friendDatabase(): FriendDatabase

    fun friendDao(): FriendDao
}
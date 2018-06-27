package com.nicholasworkshop.moovuptest.model

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Provides
    @Singleton
    private fun create(context: Context): FriendDatabase {
        return Room.databaseBuilder(
                context,
                FriendDatabase::class.java,
                "FriendDatabase").build()
    }
}
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
    fun friendDatabase(context: Context): FriendDatabase {
        return Room.databaseBuilder(
                context,
                FriendDatabase::class.java,
                "FriendDatabase").build()
    }

    @Provides
    @Singleton
    fun friendDao(friendDatabase: FriendDatabase): FriendDao {
        return friendDatabase.friendDao()
    }
}
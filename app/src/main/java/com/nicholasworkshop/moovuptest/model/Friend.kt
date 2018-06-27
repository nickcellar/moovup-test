package com.nicholasworkshop.moovuptest.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Entity
data class Friend(
        @PrimaryKey @ColumnInfo(name = "_id") val _id: String,
        @ColumnInfo(name = "picture") var picture: String? = null,
        @ColumnInfo(name = "name") var name: String? = null,
        @ColumnInfo(name = "email") var email: String? = null,
        @ColumnInfo(name = "latitude") var latitude: Double? = null,
        @ColumnInfo(name = "longitude") var longitude: Double? = null)

@Dao
interface FriendDao {

    @Query("SELECT * FROM friend")
    fun all(): LiveData<List<Friend>>

    @Query("SELECT * FROM friend WHERE _id IN (:friendIds)")
    fun loadAllByIds(friendIds: IntArray): List<Friend>

    @Query("SELECT * FROM friend WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): Friend

    @Insert
    fun insertAll(vararg friends: Friend)

    @Delete
    fun delete(friend: Friend)
}

@Database(entities = arrayOf(Friend::class), version = 1)
abstract class FriendDatabase : RoomDatabase() {

    abstract fun friendDao(): FriendDao
}
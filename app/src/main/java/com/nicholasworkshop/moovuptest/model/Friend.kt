package com.nicholasworkshop.moovuptest.model

import android.arch.persistence.room.*

@Entity
data class Friend(
        @PrimaryKey @ColumnInfo(name = "_id") private val _id: String? = null,
        @ColumnInfo(name = "picture") private val picture: String? = null,
        @ColumnInfo(name = "name") private val name: String? = null,
        @ColumnInfo(name = "email") private val email: String? = null,
        @ColumnInfo(name = "latitude") private val latitude: Double? = null,
        @ColumnInfo(name = "longitude") private val longitude: Double? = null)

@Dao
interface FriendDao {

    @Query("SELECT * FROM friend")
    fun all(): List<Friend>

    @Query("SELECT * FROM friend WHERE _id IN (:friendIds)")
    fun loadAllByIds(friendIds: IntArray): List<Friend>

    @Query("SELECT * FROM friend WHERE first_name LIKE :first AND " + "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Friend

    @Insert
    fun insertAll(vararg friends: Friend)

    @Delete
    fun delete(friend: Friend)
}

@Database(entities = arrayOf(Friend::class), version = 1)
abstract class FriendDatabase : RoomDatabase() {

    abstract fun friendDao(): FriendDao
}
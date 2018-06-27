package com.nicholasworkshop.moovuptest.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Entity
data class Friend(
        @PrimaryKey @ColumnInfo(name = "id") val id: String,
        @ColumnInfo(name = "picture") var picture: String? = null,
        @ColumnInfo(name = "name") var name: String? = null,
        @ColumnInfo(name = "email") var email: String? = null,
        @ColumnInfo(name = "latitude") var latitude: Double? = null,
        @ColumnInfo(name = "longitude") var longitude: Double? = null)

@Dao
interface FriendDao {

    @Query("SELECT * FROM friend")
    fun all(): LiveData<List<Friend>>

    @Query("SELECT * FROM friend WHERE id IN (:friendIds)")
    fun loadAllByIds(friendIds: IntArray): List<Friend>

    @Query("SELECT * FROM friend WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): Friend

    @Query("SELECT * FROM friend WHERE id = :id LIMIT 1")
    fun findById(id: String): Friend

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(friends: List<Friend>)

    @Delete
    fun delete(friend: Friend)
}

@Database(entities = arrayOf(Friend::class), version = 1)
abstract class FriendDatabase : RoomDatabase() {

    abstract fun friendDao(): FriendDao
}
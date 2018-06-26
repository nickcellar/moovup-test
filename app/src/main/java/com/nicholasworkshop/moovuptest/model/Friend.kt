package com.nicholasworkshop.moovuptest.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database


// {
//"location": {
//    "latitude": -51.846465,
//    "longitude": 78.133547
//},
//"picture": "http://placehold.it/32x32",
//"_id": "5ab8706a546bdf632d8d11ec",
//"name": "Tracie Combs",
//"email": "traciecombs@xiix.com"
//},

@Entity
data class User(
        @PrimaryKey @ColumnInfo(name = "_id") private val _id: String? = null,
        @ColumnInfo(name = "picture") private val picture: String? = null,
        @ColumnInfo(name = "name") private val name: String? = null,
        @ColumnInfo(name = "email") private val email: String? = null)

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun all(): LiveData<List<User>>

//    @Query("SELECT * FROM user")
//    val all: List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " + "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
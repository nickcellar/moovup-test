package com.nicholasworkshop.moovuptest.api

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey
import io.reactivex.Observable
import retrofit2.http.GET

interface FriendService {

    @GET("api/json/get/cfdlYqzrfS")
    fun get(): Observable<List<Friend>>
}

data class Friend(
        private val _id: String? = null,
        private val picture: String? = null,
        private val name: String? = null,
        private val email: String? = null)

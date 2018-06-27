package com.nicholasworkshop.moovuptest.api

import io.reactivex.Observable
import retrofit2.http.GET

interface FriendService {

    @GET("api/json/get/cfdlYqzrfS")
    fun get(): Observable<List<Friend>>

    data class Friend(
            val _id: String? = null,
            val picture: String? = null,
            val name: String? = null,
            val location: Location? = null,
            val email: String? = null)

    data class Location(
            val latitude: Double? = null,
            val longitude: Double? = null)
}
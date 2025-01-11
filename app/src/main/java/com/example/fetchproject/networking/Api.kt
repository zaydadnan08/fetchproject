package com.example.fetchproject.networking

import com.example.fetchproject.model.ListItem
import retrofit2.http.GET

interface Api {
    @GET("hiring.json")
    suspend fun getHiringList(): List<ListItem>
}

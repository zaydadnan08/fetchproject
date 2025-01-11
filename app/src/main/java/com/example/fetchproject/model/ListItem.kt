package com.example.fetchproject.model

import com.google.gson.annotations.SerializedName

data class ListItem (
    @SerializedName("id")
    val id: Int,

    @SerializedName("listId")
    val listId: Int,

    @SerializedName("name")
    val name: String?,
)
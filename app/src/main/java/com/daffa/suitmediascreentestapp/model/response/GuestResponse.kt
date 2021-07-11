package com.daffa.suitmediascreentestapp.model.response

import com.google.gson.annotations.SerializedName

data class GuestResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("birthdate")
    val birthdate: String
)

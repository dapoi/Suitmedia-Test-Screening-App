package com.daffa.suitmediascreentestapp.network

import com.daffa.suitmediascreentestapp.model.entity.GuestEntity
import com.daffa.suitmediascreentestapp.model.response.GuestResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndPoint {
    @GET("596dec7f0f000023032b8017")
    fun getDataGuest(): Call<List<GuestResponse>>
}
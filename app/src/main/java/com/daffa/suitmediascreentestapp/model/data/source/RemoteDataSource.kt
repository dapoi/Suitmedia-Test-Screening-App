package com.daffa.suitmediascreentestapp.model.data.source

import android.util.Log
import com.daffa.suitmediascreentestapp.model.response.GuestResponse
import com.daffa.suitmediascreentestapp.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    fun getDataGuest(callback: LoadDataGuest) {
        ApiClient.getRetrofitService().getDataGuest()
            .enqueue(object : Callback<List<GuestResponse>> {
                override fun onResponse(
                    call: Call<List<GuestResponse>>,
                    response: Response<List<GuestResponse>>
                ) {
                    callback.receivedDataGuest(response.body())
                }

                override fun onFailure(call: Call<List<GuestResponse>>, t: Throwable) {
                    Log.e(TAG, "Failure ${t.message}")
                }
            })
    }

    interface LoadDataGuest {
        fun receivedDataGuest(guestResponse: List<GuestResponse>?)
    }

    companion object {
        const val TAG = "Remote Data Source"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = instance ?: synchronized(this) {
            RemoteDataSource().apply { instance = this }
        }
    }
}
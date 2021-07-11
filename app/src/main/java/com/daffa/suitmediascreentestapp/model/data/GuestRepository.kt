package com.daffa.suitmediascreentestapp.model.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.daffa.suitmediascreentestapp.model.data.source.GuestDataSource
import com.daffa.suitmediascreentestapp.model.data.source.RemoteDataSource
import com.daffa.suitmediascreentestapp.model.entity.GuestEntity
import com.daffa.suitmediascreentestapp.model.response.GuestResponse

class GuestRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    GuestDataSource {

    override fun loadDataGuest(): LiveData<List<GuestEntity>> {
        val getGuest = MutableLiveData<List<GuestEntity>>()

        remoteDataSource.getDataGuest(object : RemoteDataSource.LoadDataGuest {
            override fun receivedDataGuest(guestResponse: List<GuestResponse>?) {
                val listGuest = ArrayList<GuestEntity>()
                if (guestResponse != null) {
                    for (guest in guestResponse) {
                        with(guest) {
                            val guest = GuestEntity(id, name, birthdate)
                            listGuest.add(guest)
                        }
                    }
                    getGuest.postValue(listGuest)
                }
            }
        })
        return getGuest
    }

    companion object {
        @Volatile
        private var instance: GuestRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): GuestRepository =
            instance ?: synchronized(this) {
                GuestRepository(remoteDataSource).apply { instance = this }
            }
    }
}
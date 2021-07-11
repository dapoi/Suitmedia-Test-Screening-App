package com.daffa.suitmediascreentestapp.model.data.source

import androidx.lifecycle.LiveData
import com.daffa.suitmediascreentestapp.model.entity.GuestEntity

interface GuestDataSource {
    fun loadDataGuest(): LiveData<List<GuestEntity>>
}
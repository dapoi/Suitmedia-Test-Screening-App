package com.daffa.suitmediascreentestapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.daffa.suitmediascreentestapp.model.data.GuestRepository
import com.daffa.suitmediascreentestapp.model.entity.GuestEntity

class GuestViewModel(private val guestRepository: GuestRepository) : ViewModel() {

    fun getListGuest(): LiveData<List<GuestEntity>> = guestRepository.loadDataGuest()

}
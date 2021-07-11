package com.daffa.suitmediascreentestapp.viewmodel

import androidx.lifecycle.ViewModel
import com.daffa.suitmediascreentestapp.model.entity.EventEntity
import com.daffa.suitmediascreentestapp.util.EventDummy

class EventViewModel : ViewModel() {
    fun getDataEvents(): List<EventEntity> = EventDummy.dataEvent()
}
package com.daffa.suitmediascreentestapp.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventEntity(
    val image: Int,
    var name: String,
    val date: String
): Parcelable
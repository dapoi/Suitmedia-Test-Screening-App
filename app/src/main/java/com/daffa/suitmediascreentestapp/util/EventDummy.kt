package com.daffa.suitmediascreentestapp.util

import com.daffa.suitmediascreentestapp.R
import com.daffa.suitmediascreentestapp.model.entity.EventEntity

object EventDummy {
    fun dataEvent(): List<EventEntity> {
        val events = ArrayList<EventEntity>()

        events.add(
            EventEntity(
                R.drawable.ngoding,
                "Perlombaan Pembuatan Aplikasi Android",
                "17 Agustus 2021"
            )
        )

        events.add(
            EventEntity(
                R.drawable.band,
                "Festival Musik Pop Punk",
                "20 September 2021"
            )
        )

        events.add(
            EventEntity(
                R.drawable.seminar,
                "Seminar Kemanusiaan",
                "28 Oktober 2021"
            )
        )

        events.add(
            EventEntity(
                R.drawable.kursus,
                "Kursus Bahasa Inggris",
                "22 Desember 2021"
            )
        )

        return events
    }
}
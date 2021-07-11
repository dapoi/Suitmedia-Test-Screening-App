package com.daffa.suitmediascreentestapp.di

import android.content.Context
import com.daffa.suitmediascreentestapp.model.data.GuestRepository
import com.daffa.suitmediascreentestapp.model.data.source.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): GuestRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return GuestRepository.getInstance(remoteDataSource)
    }
}
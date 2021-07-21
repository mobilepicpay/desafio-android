package com.picpay.desafio.android.retrofit.webclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.picpay.desafio.android.model.network.User
import com.picpay.desafio.android.repository.Resource
import com.picpay.desafio.android.retrofit.AppRetrofitConfig
import com.picpay.desafio.android.retrofit.service.PicPayService



class PicPayWebClient(
    private val service: PicPayService = AppRetrofitConfig().service
) {

    suspend fun getAllUsers() = service.getUsers()
}
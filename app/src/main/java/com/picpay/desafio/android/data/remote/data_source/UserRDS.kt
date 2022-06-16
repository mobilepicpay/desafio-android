package com.picpay.desafio.android.data.remote.data_source

import com.picpay.desafio.android.data.remote.model.UserRM
import com.picpay.desafio.android.domain.model.User
import retrofit2.Call
import retrofit2.http.GET

// UserRM Remote Data source
interface UserRDS {

    @GET("users")
    fun getUsers(): List<UserRM>
}
package com.picpay.desafio.android.data.remote.data_source

import com.picpay.desafio.android.data.remote.model.User
import retrofit2.Call
import retrofit2.http.GET

// User Remote Data source
interface UserRDS {

    @GET("users")
    fun getUsers(): Call<List<User>>
}
package com.picpay.desafio.android.data.network.api.services

import com.picpay.desafio.android.data.network.api.Retrofit
import com.picpay.desafio.android.data.network.reponses.ApiResponse
import com.picpay.desafio.android.models.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

import retrofit2.http.GET
@ExperimentalCoroutinesApi
interface PicPayService {
   @GET("users")
    fun getUsers(): Flow<ApiResponse<List<User>>>
    companion object {
        operator fun invoke(url:String = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"): PicPayService {
            return Retrofit
                .invoke(url)
                .create(PicPayService::class.java)
        }
    }
}
package com.picpay.desafio.android.remote.repository

import com.picpay.desafio.android.remote.service.PicPayService
import com.picpay.desafio.android.remote.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PicPayRepository(private val service: PicPayService) {

    fun getUsers(listener: ApiListener<List<User>>) {
        val call: Call<List<User>> = service.getUsers()
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.body() != null) {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                listener.onFailure()
            }

        })
    }
}
package com.picpay.desafio.android.data.network.adapter

import com.picpay.desafio.android.data.network.reponses.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.awaitResponse
import java.lang.reflect.Type

@ExperimentalCoroutinesApi
class FlowCallAdapter<T : Any>(
    private val responseType: Type
) : CallAdapter<T, Flow<ApiResponse<T>>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<T>): Flow<ApiResponse<T>> = flow {
       val response = call.awaitResponse()
        println(response)
        emit(ApiResponse.create(response))
    }.catch { error ->
        emit(ApiResponse.create(error))
    }
}
package com.picpay.desafio.android.data.source.remote

import com.picpay.desafio.android.core.DataResult
import com.picpay.desafio.android.core.RemoteDataSourceError
import com.picpay.desafio.android.data.model.UserEntity
import retrofit2.HttpException
import retrofit2.Response

class UserRemoteDataSource constructor(private val service: PicPayService) {

    suspend fun getUsers(): DataResult<List<UserEntity>> {
        return getResponse(request = { service.getUsers() })
    }

    private suspend fun <T : Any> getResponse(
        request: suspend () -> Response<T>
    ): DataResult<T> {
        return try {
            val response = request()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                DataResult.Success(body)
            } else {
                DataResult.Error(RemoteDataSourceError(response.code(), response.message()))
            }
        } catch (e: HttpException) {
            DataResult.Error(RemoteDataSourceError(e.code(), e.message()))
        } catch (ignore: Throwable) {
            DataResult.Error(
                RemoteDataSourceError(0, "another error not HttpException")
            )
        }
    }
}

package com.picpay.desafio.android.data.source.remote

import com.picpay.desafio.android.core.Outcome
import com.picpay.desafio.android.core.RemoteDataSourceError
import com.picpay.desafio.android.data.model.UserEntity
import retrofit2.HttpException
import retrofit2.Response

class UserRemoteDataSource constructor(private val service: PicPayService) {

    suspend fun getUsers(): Outcome<List<UserEntity>> {
        return getResponse(request = { service.getUsers() })
    }

    private suspend fun <T : Any> getResponse(
        request: suspend () -> Response<T>
    ): Outcome<T> {
        return try {
            val response = request()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                Outcome.Success(body)
            } else {
                Outcome.Error(RemoteDataSourceError(response.code(), response.message()))
            }
        } catch (e: HttpException) {
            Outcome.Error(RemoteDataSourceError(e.code(), e.message()))
        } catch (ignore: Throwable) {
            Outcome.Error(
                RemoteDataSourceError(0, "another error not HttpException")
            )
        }
    }
}

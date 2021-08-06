package com.picpay.desafio.android.data.utils

import com.picpay.desafio.android.data.network.reponses.ApiErrorResponse
import com.picpay.desafio.android.data.network.reponses.ApiResponse
import com.picpay.desafio.android.data.network.reponses.ApiSuccessResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
inline fun <DB, REMOTE> networkBoundResource(
    crossinline fetchFromLocal: () -> Flow<DB?>,
    crossinline shouldFetchFromRemote: (DB?) -> Boolean = {
        it == null
    },
    crossinline fetchFromRemote: () -> Flow<ApiResponse<REMOTE>>,
    crossinline processRemoteResponse: (response: ApiSuccessResponse<REMOTE>) -> Unit = { Unit },
    crossinline saveRemoteData: (REMOTE) -> Unit = { Unit },
    crossinline onFetchFailed: (errorBody: String?, statusCode: Int) -> Unit = { _: String?, _: Int -> Unit },
    isRefresh: Boolean
) = flow<Resource<DB>> {

    emit(Resource.loading())
    var localData: DB? = null
    fetchFromLocal()
        .catch { e ->
            e.printStackTrace()
        }.collect {
            localData = it
        }

    val remote = shouldFetchFromRemote(localData)

    if (isRefresh || remote) {
        fetchFromRemote()
            .catch { e ->
                Resource.error(
                    e.message ?: "Erro desconhecido",
                    e,
                    null
                )
            }.onCompletion {
                print("onCompletion")
            }.collect { apiResponse ->
                when (apiResponse) {
                    is ApiSuccessResponse -> {
                        processRemoteResponse(apiResponse)
                        apiResponse.body?.let { saveRemoteData(it) }
                        emitAll(fetchFromLocal().map { dbData ->
                            Resource.success(dbData)
                        })
                    }

                    is ApiErrorResponse -> {
                        onFetchFailed(apiResponse.errorMessage, apiResponse.statusCode)
                        emitAll(
                            flowOf(
                                Resource.error(
                                    apiResponse.errorMessage,
                                    null,
                                    null
                                )
                            )
                        )
                    }
                }
            }
    } else {
        emitAll(fetchFromLocal()
            .catch { e ->
                Resource.error(
                    e.message ?: "Erro desconhecido",
                    e,
                    null
                )
            }.onCompletion {
                print("onCompletion")
            }.map {
                Resource.success(it)
            })
    }
}
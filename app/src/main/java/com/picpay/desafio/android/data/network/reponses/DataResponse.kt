package com.picpay.desafio.android.data.network.reponses

data class DataResponse<out T>(val status: StatusResponse,
                               val data: T?,
                               val message: String?
) {
    companion object {
        fun <T> SUCCESS(data: T?): DataResponse<T> {
            return DataResponse(
                StatusResponse.SUCCESS,
                data,
                null
            )
        }

        fun <T> ERROR(message: String?): DataResponse<T> {
            return DataResponse(
                StatusResponse.ERROR,
                null,
                message
            )
        }

        fun <T> FAILURE(): DataResponse<T> {
            return DataResponse(
                StatusResponse.FAILURE,
                null,
                null
            )
        }

        fun <T> LOADING(): DataResponse<T> {
            return DataResponse(
                StatusResponse.LOADING,
                null,
                null
            )
        }
    }
}
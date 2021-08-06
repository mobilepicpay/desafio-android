package com.picpay.desafio.android.data.utils

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val e: Throwable?
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null,
                null
            )
        }

        fun <T> error(msg: String, e: Throwable?, data: T? = null): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                msg,
                e
            )
        }

        fun <T> loading(): Resource<T> {
            return Resource(
                Status.LOADING,
                null,
                null,
                null
            )
        }
    }
}
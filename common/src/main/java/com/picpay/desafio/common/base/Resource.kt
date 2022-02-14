package com.picpay.desafio.common.base

class Resource<T> private constructor(
    val status: Status,
    val value: T? = null,
    val error: Throwable? = null
) {

    enum class Status {
        SUCCESS, ERROR, LOADING, EMPTY
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(exception: Throwable?, data: T? = null): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                exception
            )
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING)
        }

        fun <T> empty(): Resource<T> {
            return Resource(Status.EMPTY)
        }
    }

    fun <T> transform(data: T? = null): Resource<T> {
        return Resource(status, data, error)
    }

}

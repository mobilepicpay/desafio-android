package com.picpay.desafio.android.core_network.models

/**
 * Wrapper class to retrofit response. The success class is intended to be used as wrapper for successful requests, the error class
 * is responsible to wrap the error caused by the Retrofit exception
 * */
sealed class Response<out T> {

    data class Success<T>(val data: T) : Response<T>()
    data class Error<T>(val networkError: NetworkError) : Response<T>()

    /**
     * Maps a Response<T> if it's a success instance
     * @param mapper function that maps the request data type to the domain data type
     * */
    fun <Domain> mapResponse(mapper: (T) -> Domain): Response<Domain> {
        if (this is Error) return Error(this.networkError)

        return Success(mapper((this as Success).data))
    }

    /**
     * Helper function to manage the Response wrapper content, having callbacks for each of the possible outcomes
     * @param onSuccess lambda callback containing the request data
     * @param onError lambda callback containing the request error
     * */
    fun handleResult(
        onSuccess: (T) -> Unit,
        onError: (NetworkError) -> Unit
    ) {
        if (this is Success) onSuccess(this.data)
        else onError((this as Error).networkError)

    }

}
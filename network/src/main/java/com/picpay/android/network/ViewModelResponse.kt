package com.picpay.android.network


sealed class ViewModelResponse<out T> {

    data class Loading(val load: Boolean) : ViewModelResponse<Nothing>()

    data class Success<T>(val value: T) : ViewModelResponse<T>()

    data class Error(val customError: com.picpay.android.network.CustomError) : ViewModelResponse<Nothing>()
}
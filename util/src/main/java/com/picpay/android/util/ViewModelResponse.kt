package com.picpay.android.util


sealed class ViewModelResponse<out T> {

    data class Loading(val load: Boolean) : ViewModelResponse<Nothing>()

    data class Success<T>(val value: T) : ViewModelResponse<T>()

    data class Error<T>(val exception: Throwable) : ViewModelResponse<T>()
}
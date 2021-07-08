package com.picpay.desafio.android.ui.presentation

sealed class ViewState<T>(
    val progress: Boolean = false,
    val success: Boolean = false,
    val empty: Boolean = false,
    val error: Boolean = false
) {
    class Loading<T> : ViewState<T>(progress = true)

    class Success<T>(val data: T) : ViewState<T>(success = true)

    class Empty<T> : ViewState<T>(empty = true)

    class Error<T>(val message: String) : ViewState<T>(error = true)
}
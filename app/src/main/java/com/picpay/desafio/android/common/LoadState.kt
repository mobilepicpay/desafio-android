package com.picpay.desafio.android.common

sealed class LoadState {
    object READY : LoadState()
    object LOADING : LoadState()
    object ERROR : LoadState()
    class SUCCESS(val data : Any?) : LoadState()
}
package com.picpay.desafio.android.commom

sealed class LoadState {
    object READY : LoadState()
    object LOADING : LoadState()
    object ERROR : LoadState()
    class SUCCESS(val data : Any?) : LoadState()
}
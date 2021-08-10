package com.picpay.desafio.android.extension

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.updateAsync(callback: T.() -> T) {
    this.value?.let {
        val newValue = callback(it)
        this.postValue(newValue)
    }
}
package com.picpay.desafio.android.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.core.State

abstract class BaseViewModel<T> : ViewModel() {
    var responseState: MutableLiveData<State<List<T>>> = MutableLiveData()
    var items: MutableLiveData<List<T>> = MutableLiveData()

    fun startLoading() {
        responseState.value = State.Loading
    }

    fun setSuccess(data: List<T>) {
        responseState.value = State.Success(data)
        items.value = data
    }

    fun setError(e: Exception) {
        responseState.value = State.Error(e)
    }
}
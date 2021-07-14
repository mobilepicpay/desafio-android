package com.picpay.desafio.android.shared.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author Vitor Herrmann on 13/07/2021
 */
abstract class BaseViewModel : ViewModel() {

    protected val mOnShowLoading = MutableLiveData<Unit>()
    val onShowLoading: LiveData<Unit> get() = mOnShowLoading

    protected val mOnHideLoading = MutableLiveData<Unit>()
    val onHideLoading: LiveData<Unit> get() = mOnHideLoading

    protected val mOnError = MutableLiveData<Throwable>()
    val onError: LiveData<Throwable> get() = mOnError
}

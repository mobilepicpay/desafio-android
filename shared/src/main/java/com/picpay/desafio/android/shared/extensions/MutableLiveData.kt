package com.picpay.desafio.android.shared.extensions

import androidx.lifecycle.MutableLiveData

/**
 * @author Vitor Herrmann on 13/07/2021
 */
fun MutableLiveData<Unit>.postUnit() = postValue(Unit)

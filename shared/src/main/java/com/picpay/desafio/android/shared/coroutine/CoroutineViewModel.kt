package com.picpay.desafio.android.shared.coroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.shared.presentation.lifecycle.SingleLiveData
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

@Suppress("PropertyName")
abstract class CoroutineViewModel<STATE, EVENT>(coroutineService: CoroutineService) : ViewModel() {
    protected val _state = MutableLiveData<STATE>()

    val state: LiveData<STATE> get() = _state

    protected val _event = SingleLiveData<EVENT>()

    val event: LiveData<EVENT> get() = _event

    private val supervisorJob = SupervisorJob()

    protected val scope = CustomCoroutineScope(supervisorJob, coroutineService)

    override fun onCleared() {
        super.onCleared()
        supervisorJob.cancelChildren()
    }
}
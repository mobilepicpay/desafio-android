package com.picpay.desafio.android.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.OpenForTesting
import com.picpay.desafio.android.data.network.reponses.DataResponse
import com.picpay.desafio.android.data.repositories.UserRepositoryInterface
import com.picpay.desafio.android.data.utils.Resource
import com.picpay.desafio.android.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
@OpenForTesting
class UserViewModel(private val repository: UserRepositoryInterface): ViewModel() {
    private val _users = MutableLiveData<DataResponse<List<User>>>()
    val users: LiveData<DataResponse<List<User>>>
        get() = _users

    init {
        getUsers(false)
    }

    final fun getUsers(isRefresh: Boolean) {
        try {
            repository.getUsers(isRefresh)
                .flowOn(Dispatchers.IO)
                .onStart {
                    _users.value = DataResponse.LOADING()
                }.catch {
                    _users.value = DataResponse.FAILURE()
                }.onCompletion {
                    if (it == null) println("Completed successfully")
                }.map {
                    when (it?.status) {
                        Resource.Status.LOADING -> {
                            _users.value = DataResponse.LOADING()
                        }
                        Resource.Status.SUCCESS -> {
                            _users.value = DataResponse.SUCCESS(it.data)
                        }
                        Resource.Status.ERROR -> {
                            _users.value =
                                DataResponse.FAILURE()
                        }
                    }
                }.launchIn(viewModelScope)
        } catch (e: Exception) {
            _users.value = DataResponse.FAILURE()
        }
    }
}
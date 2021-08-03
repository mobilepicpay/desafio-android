package com.picpay.android.user.presentation

import androidx.lifecycle.*
import com.picpay.android.user.usedatasoucer.User
import com.picpay.android.user.usedatasoucer.UserRepository
import com.picpay.android.user.usedatasoucer.local.UserLocalRepository
import com.picpay.android.user.usedatasoucer.network.UserNetWorkRepository
import com.picpay.android.util.ViewModelResponse
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserNetWorkRepository,
    private val userLocalRepository: UserLocalRepository
) : ViewModel(), LifecycleObserver {

    val userLiveData = MutableLiveData<ViewModelResponse<List<User>>>()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun getUsers() {
        viewModelScope.launch {

            runCatching {

                userLiveData.value = ViewModelResponse.Loading(true)
                userRepository.getUsers()

            }.onSuccess {
                userLiveData.value = ViewModelResponse.Success(it)
            }.onFailure {
                userLiveData.value = ViewModelResponse.Error(it)
            }

            userLiveData.value = ViewModelResponse.Loading(false)
        }
    }

}
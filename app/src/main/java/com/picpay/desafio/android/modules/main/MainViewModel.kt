package com.picpay.desafio.android.modules.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.models.User
import com.picpay.desafio.android.modules.base.BaseViewModel
import com.picpay.desafio.android.repositories.UserRepository
import com.picpay.desafio.android.utils.pokos.ErrorMessageViewObject
import com.picpay.desafio.android.utils.pokos.UserViewItem
import com.picpay.desafio.android.utils.sealeds.DataConsumptionStatus
import com.picpay.desafio.android.utils.sealeds.DataErrorException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val userViewItems = MutableLiveData<List<UserViewItem>>()
    val userViewItemsLiveData: LiveData<List<UserViewItem>> = userViewItems

    private val requestError = MutableLiveData<ErrorMessageViewObject>()
    val requestErrorLiveData: LiveData<ErrorMessageViewObject> = requestError

    private val showSwipeToRefreshLoading = MutableLiveData<Boolean>()
    val showSwipeToRefreshLoadingLiveData: LiveData<Boolean> = showSwipeToRefreshLoading

    init {
        getUsers()
    }

    fun onRetryGetUsers() {
        getUsers()
    }

    fun onRefreshUsers() {
        refreshUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            userRepository.getUsers().collect {
                when (it) {
                    is DataConsumptionStatus.Loading -> onLoadingEmitted(it.isLoading)
                    is DataConsumptionStatus.Success -> onDataEmitted(it.data)
                    is DataConsumptionStatus.Error -> onErrorEmitted(it.exception)
                }
            }
        }
    }

    private fun refreshUsers() {
        viewModelScope.launch {
            userRepository.refreshUsers().collect {
                when (it) {
                    is DataConsumptionStatus.Loading -> onRefreshLoadingEmitted(it.isLoading)
                    is DataConsumptionStatus.Success -> onDataEmitted(it.data)
                    is DataConsumptionStatus.Error -> onErrorEmitted(it.exception)
                }
            }
        }
    }

    private fun onLoadingEmitted(isLoading: Boolean) {
        when {
            isLoading && userViewItems.value.isNullOrEmpty() -> showLoading.value = true
            !isLoading && showLoading.value == true -> showLoading.value = false
        }
    }

    private fun onRefreshLoadingEmitted(isLoading: Boolean) {
        showSwipeToRefreshLoading.value = isLoading
    }

    private fun onDataEmitted(users: List<User>) {
        userViewItems.value = convertToViewItems(users)
    }

    private fun onErrorEmitted(dataErrorException: DataErrorException) {
        requestError.value = ErrorMessageViewObject(
            dataErrorException.messageResId,
            dataErrorException.actionResId
        )
    }

    private fun convertToViewItems(users: List<User>): List<UserViewItem> {
        return users.map { user ->
            buildUserViewItem(user)
        }
    }

    private fun buildUserViewItem(user: User): UserViewItem {
        return UserViewItem(user.name, user.image, user.username)
    }
}

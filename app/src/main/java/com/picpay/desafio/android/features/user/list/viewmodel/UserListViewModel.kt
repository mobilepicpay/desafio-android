package com.picpay.desafio.android.features.user.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.domain.result.Result
import com.picpay.desafio.domain.models.User
import com.picpay.desafio.domain.usecases.GetUserList
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserListViewModel(
    private val getUserList: GetUserList
) : ViewModel() {

    private val usersLiveData = MutableLiveData<Result<List<User>>>()

    fun getUsersLiveDate() = usersLiveData

    init {
        viewModelScope.launch {
            getUserList.execute().collect { userResult ->
                usersLiveData.value = userResult
            }
        }
    }
}
package com.picpay.desafio.android.features.user.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.domain.models.User
import com.picpay.desafio.domain.result.Result
import com.picpay.desafio.domain.usecases.GetUserDetail
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserDetailViewModel(
    private val getUserDetail: GetUserDetail
) : ViewModel() {

    private val userLiveData = MutableLiveData<Result<User>>()

    fun getUserLiveDate() = userLiveData

    fun getUser(userId: Int) {
        viewModelScope.launch {
            getUserDetail.execute(userId).collect { result ->
                userLiveData.value = result
            }
        }
    }
}
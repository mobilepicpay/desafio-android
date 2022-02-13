package com.picpay.desafio.userlist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.common.base.Resource
import com.picpay.desafio.userlist.domain.model.User
import com.picpay.desafio.userlist.domain.usecase.GetUserListUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserListViewModel(
    private val useCase: GetUserListUseCase
) : ViewModel() {

    private val _userListResponse = MutableLiveData<Resource<List<User>>>()
    val userListResponse: LiveData<Resource<List<User>>>
        get() = _userListResponse

    init {
//        getList()
    }

     fun getList() {
        viewModelScope.launch {
            useCase.execute(Unit)
                .collect {
                    _userListResponse.postValue(it)
                }
        }
    }

}
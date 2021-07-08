package com.picpay.desafio.android.ui.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.FetchUseCase
import kotlinx.coroutines.launch
import com.picpay.desafio.android.domain.Result

class UserViewModel(
    private val mapper: UserMapper,
    private val userUseCase: FetchUseCase
) : ViewModel() {

    val userState = MutableLiveData<ViewState<ArrayList<UserViewObject>>>()

    fun fetchUsers() {
        userState.value = ViewState.Loading()

        viewModelScope.launch {
            when (val result = userUseCase.execute()) {
                is Result.Success -> {
                    if (result.data.isNullOrEmpty()) {
                        userState.value = ViewState.Empty()
                    } else {
                            userState.value =
                                ViewState.Success((mapper.responseToViewObject(result.data)))
                    }
                }
                is Result.Error -> userState.value = ViewState.Error(result.error.message)
            }
        }
    }
}
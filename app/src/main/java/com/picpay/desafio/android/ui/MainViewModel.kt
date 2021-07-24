package com.picpay.desafio.android.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.Resource
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.usecase.PicPayUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: PicPayUseCase) : ViewModel() {

    private val _users by lazy { MutableLiveData<Resource<List<User>>>() }
    val users: LiveData<Resource<List<User>>> get() = _users

    fun fetchUsers() = viewModelScope.launch {
        _users.postValue(Resource.Loading())
        when (val response = useCase.invoke()) {
            is Resource.Success -> {
                Log.d("ALEDEV","Success")
            }
            else -> {
                Log.d("ALEDEV","ERROR")
            }
        }

    }
}
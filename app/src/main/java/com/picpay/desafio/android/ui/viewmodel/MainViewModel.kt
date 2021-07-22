package com.picpay.desafio.android.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.repository.UserDataRepository
import kotlinx.coroutines.launch


class MainViewModel(private val userDataRepository: UserDataRepository) : ViewModel() {

    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> = _userList

    private val _loadingProgress = MutableLiveData<Unit>()
    val loadingProgress: LiveData<Unit> = _loadingProgress

    private val _toastError = MutableLiveData<Unit>()
    val toastError: LiveData<Unit> = _toastError



    fun getUser() {
        _loadingProgress.postValue(Unit)
        viewModelScope.launch {
            try {
                val response = userDataRepository.getUserList()
                when (response.isNotEmpty()) {
                    true -> {
                        _userList.postValue(response)
                        saveUserCache(response)
                    }
                    else -> _toastError.postValue(Unit)
                }
            }
            catch (e:Exception) {
                _toastError.postValue(Unit)
            }
        }
    }

    private fun saveUserCache(response: List<User>) {
        viewModelScope.launch {
            userDataRepository.saveUserCache(response)

            Log.i("USER_LIST_CACHE", userDataRepository.getUserLocal().toString())
        }
    }
}
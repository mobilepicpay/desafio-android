package com.picpay.desafio.android.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.repository.UserDataRepository
import com.picpay.desafio.android.ui.utils.asLiveData
import kotlinx.coroutines.launch


class MainViewModel(private val userDataRepository: UserDataRepository) : ViewModel() {

    private val _userList = MutableLiveData<List<User>>()
    val userList = _userList.asLiveData()

    private val _loadingProgress = MutableLiveData<Unit>()
    val loadingProgress = _loadingProgress.asLiveData()

    private val _toastError = MutableLiveData<Unit>()
    val toastError = _toastError.asLiveData()

    fun getUserList() {
        _loadingProgress.postValue(Unit)

        viewModelScope.launch {
            try {
                val response = userDataRepository.getUserList()
                if (response.isNotEmpty()) {
                    _userList.postValue(response)
                    saveUserCache(response)
                } else _toastError.postValue(Unit)
            } catch (e: Exception) {
                _toastError.postValue(Unit)
            }
        }
    }

    private fun saveUserCache(response: List<User>) {
        viewModelScope.launch {
            userDataRepository.saveUserCache(response)

            Log.i("USER_LIST_CACHE", userDataRepository.getUserCache().toString())
        }
    }
}
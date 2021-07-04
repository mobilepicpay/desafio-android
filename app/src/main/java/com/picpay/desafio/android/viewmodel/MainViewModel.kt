package com.picpay.desafio.android.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.picpay.desafio.android.Constants
import com.picpay.desafio.android.remote.User
import com.picpay.desafio.android.remote.repository.ApiListener
import com.picpay.desafio.android.remote.repository.PicPayRepository

class MainViewModel(private val repository: PicPayRepository) : ViewModel() {

    private val _progressBar = MutableLiveData<Int>()
    val progressBar: LiveData<Int> = _progressBar

    private val _recyclerView = MutableLiveData<Int>()
    val recyclerView: LiveData<Int> = _recyclerView

    private val _usersList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> = _usersList

    private val _responseStatus = MutableLiveData<Int>()
    val responseStatus: LiveData<Int> = _responseStatus

    fun getUsersRepository() {
        val listener = object : ApiListener<List<User>> {
            override fun onSuccess(list: List<User>) {
                _responseStatus.value = Constants.success
                _progressBar.value = View.GONE
                _usersList.value = list
            }

            override fun onFailure() {
                _responseStatus.value = Constants.failure
                _progressBar.value = View.GONE
                _recyclerView.value = View.GONE
            }

        }

        repository.getUsers(listener)
    }

    class MainViewModelFactory(
        private val repository: PicPayRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }
}
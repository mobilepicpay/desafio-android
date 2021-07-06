package com.picpay.desafio.android.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.picpay.desafio.android.remote.model.User
import com.picpay.desafio.android.remote.repository.ApiListener
import com.picpay.desafio.android.remote.repository.DefaultRepository
import com.picpay.desafio.android.remote.repository.PicPayRepository
import com.picpay.desafio.android.utls.Constants

class MainViewModel(private val repository: DefaultRepository) : ViewModel() {

    private val _progressBar = MutableLiveData<Int>()
    val progressBar: LiveData<Int> = _progressBar

    private val _recyclerView = MutableLiveData<Int>()
    val recyclerView: LiveData<Int> = _recyclerView

    private val _usersList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> = _usersList

    private val _responseStatus = MutableLiveData<Int>()
    val responseStatus: LiveData<Int> = _responseStatus

    fun getUsers() {
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

}
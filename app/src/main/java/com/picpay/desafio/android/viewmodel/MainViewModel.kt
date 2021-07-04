package com.picpay.desafio.android.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.picpay.desafio.android.Constants
import com.picpay.desafio.android.remote.PicPayService
import com.picpay.desafio.android.remote.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainViewModel(networkService: Retrofit) : ViewModel() {

    private val service: PicPayService = networkService.create(PicPayService::class.java)

    private val _progressBar = MutableLiveData<Int>()
    val progressBar: LiveData<Int> = _progressBar

    private val _recyclerView = MutableLiveData<Int>()
    val recyclerView: LiveData<Int> = _recyclerView

    private val _usersList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> = _usersList

    private val _responseStatus = MutableLiveData<Int>()
    val responseStatus: LiveData<Int> = _responseStatus


    fun getUsers() {
        service.getUsers()
            .enqueue(object : Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    _responseStatus.value = Constants.failure

                    _progressBar.value = View.GONE
                    _recyclerView.value = View.GONE
                }

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    _responseStatus.value = Constants.success
                    _progressBar.value = View.GONE
                    _usersList.value = response.body()
                }
            })
    }

    class MainViewModelFactory(
        private val networkService: Retrofit
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(networkService) as T
        }
    }
}
package com.picpay.desafio.android.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.PicPayService
import com.picpay.desafio.android.User
import com.picpay.desafio.android.commom.LoadState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersListViewModel : ViewModel() {

    val usersListState = MutableLiveData<LoadState>(LoadState.READY)

    fun loadUsers(service: PicPayService) {
        service.getUsers()
            .enqueue(object : Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    usersListState.postValue(LoadState.ERROR)
                }

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    usersListState.postValue(LoadState.SUCCESS(response.body()))
                }
            })
    }
}
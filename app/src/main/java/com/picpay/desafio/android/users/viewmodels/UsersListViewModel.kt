package com.picpay.desafio.android.users.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.users.repo.UsersApi
import com.picpay.desafio.android.users.repo.UserResponse
import com.picpay.desafio.android.common.LoadState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersListViewModel : ViewModel() {

    val usersListState = MutableLiveData<LoadState>(LoadState.READY)

    fun loadUsers(service: UsersApi) {
        service.getUsers()
            .enqueue(object : Callback<List<UserResponse>> {
                override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                    usersListState.postValue(LoadState.ERROR)
                }

                override fun onResponse(call: Call<List<UserResponse>>, response: Response<List<UserResponse>>) {
                    usersListState.postValue(LoadState.SUCCESS(response.body()))
                }
            })
    }
}
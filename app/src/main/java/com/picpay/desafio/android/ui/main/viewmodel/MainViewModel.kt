package com.picpay.desafio.android.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    private val _getUserError = MutableLiveData<String?>()
    val getUserError: LiveData<String?>
        get() = _getUserError

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            repository.getUsers()
                .enqueue(object : Callback<List<User>> {
                    override fun onFailure(call: Call<List<User>>, t: Throwable) {
                        _getUserError.postValue(t.message)
                    }

                    override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                        _users.postValue(response.body()!!)
                        _getUserError.postValue(null)
                        Log.d("Viewmodel", response.body().toString())
                    }
                })
        }
    }
}

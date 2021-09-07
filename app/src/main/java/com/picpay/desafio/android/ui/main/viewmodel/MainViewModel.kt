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

    private val mainViewState = MainViewState()
    private val _state = MutableLiveData(mainViewState)
    val state: LiveData<MainViewState>
        get() = _state

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            repository.getUsers()
                .enqueue(object : Callback<List<User>> {
                    override fun onFailure(call: Call<List<User>>, t: Throwable) {
                        _state.postValue(mainViewState.postError(t.message))
                    }

                    override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                        _state.postValue(mainViewState.postSuccess(response.body()))
                        Log.d("Viewmodel", response.body().toString())
                    }
                })
        }
    }
}

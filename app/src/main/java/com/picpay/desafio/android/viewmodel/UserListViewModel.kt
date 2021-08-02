package com.picpay.desafio.android.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.service.UserRestApiTask

class UserListViewModel : ViewModel() {
    private val userRestApiTask = UserRestApiTask()

    private var _usersList = MutableLiveData<List<User>>()
    val usersList: LiveData<List<User>>
        get() = _usersList

    fun init(){
        getUsers()
    }

    private fun getUsers() {
        Thread {
            try{
                val request = userRestApiTask.retrofitApi().getUsers().execute()

                if (request.isSuccessful) {
                    request.body()?.let {
                        _usersList.postValue(it)
                    }
                } else {
                    request.errorBody()?.let {
                        Log.d(TAG, it.toString())
                    }
                }
            }catch (exception: Exception){
                Log.d(TAG, exception.message.toString())
            }
        }.start()
    }

}




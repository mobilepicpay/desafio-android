package com.picpay.desafio.android.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.UserRepository
import com.picpay.desafio.android.service.UserRestApiTask
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    userRepository: UserRepository
) : ViewModel() {

    val users = userRepository.getUsers().asLiveData()

//    companion object {
//        const val TAG = "UserRepository"
//    }
//
//    private val userRestApiTask = UserRestApiTask()
//    private val userRepository = UserRepository(userRestApiTask)


//    private var _usersList = MutableLiveData<List<User>>()
//    val usersList: LiveData<List<User>>
//        get() = _usersList
//
//    fun init(){
//        getUsers()
//    }
//
//    private fun getUsers() {
//        Thread {
//            try {
//                _usersList.postValue(userRepository.getUsers())
//            } catch (exception: Exception) {
//                Log.d(TAG, exception.message.toString())
//            }
//        }.start()
//    }

}




package com.picpay.desafio.android.ui.viewmodel

import androidx.lifecycle.*
import com.picpay.desafio.android.model.network.User
import com.picpay.desafio.android.repository.Resource
import com.picpay.desafio.android.repository.UserRepository
import com.picpay.desafio.android.retrofit.service.PicPayService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.notify

class MainActivityViewModel(
    private val repository: UserRepository
): ViewModel() {

    var userList = listOf<User>()
    var isFirstScroll = true
    var firstItemVisible = -1

    fun getAllUsers() = liveData{
        if(userList.isNotEmpty()){
           emit(Resource.Success(userList))
        }else{
           emit(repository.getAllUsers())
        }
    }
}

//class MainActivityViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory{
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return MainActivityViewModel(repository) as T
//    }
//}
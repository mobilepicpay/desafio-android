package com.picpay.desafio.android.ui.home

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.utils.Constants
import com.picpay.desafio.android.utils.Constants.UNEXPECTED_ERROR
import com.picpay.desafio.android.utils.network.SafeCall
import com.picpay.desafio.android.utils.service.PicPayService
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.reflect.Type

class UserViewModel(private val service: PicPayService, private val prefs: SharedPreferences)
    : ViewModel(), KoinComponent {

    private var _state: MutableLiveData<UserListState> = MutableLiveData()
    val state : LiveData<UserListState> = _state
    private var users : List<User>? = null
//    private val prefs: SharedPreferences by inject()

    fun getUsers(isRefresh: Boolean = false) {
        viewModelScope.launch {
            if(users != null && isRefresh.not()) {
                _state.value = UserListState.Success(users ?: listOf())
                return@launch
            }
            _state.value = UserListState.UserLoading(true, isRefresh)
            val response = SafeCall.getResponse { service.getUsers() }
            when(response?.code()){
                in 200..299 -> {
                    users = response?.body()
                    _state.value = UserListState.Success(users ?: listOf())
                    saveShared(users ?: listOf())
                }
                in 300..499 -> {
                    _state.value = UserListState.Error(response?.message() ?: UNEXPECTED_ERROR)
                }
                else ->{
                    _state.value = UserListState.Error(UNEXPECTED_ERROR)
                }
            }
            _state.value = UserListState.UserLoading(false, isRefresh)
        }
    }

    private fun saveShared(users: List<User>){
        val jsonList = Gson().toJson(users)
        prefs.edit().putString(Constants.USER_LIST_KEY, jsonList).commit()
    }

    fun getSharedList() = viewModelScope.launch {
        val jsonList : String? = prefs.getString(Constants.USER_LIST_KEY, null)
        val type: Type = object : TypeToken<List<User?>>() {}.type
        if(jsonList.isNullOrEmpty().not()) {
            val userList : List<User>? = Gson().fromJson(jsonList, type)
            _state.value = UserListState.UserLoading(isLoading = false, isRefresh = true)
            _state.value = UserListState.Success(userList ?: listOf())
        } else {
            _state.value = UserListState.Error(UNEXPECTED_ERROR)
        }
    }
}

sealed class UserListState {
    class UserLoading(val isLoading: Boolean, val isRefresh: Boolean) : UserListState()
    class Success(val users: List<User>) : UserListState()
    class Error(val message: String) : UserListState()
}

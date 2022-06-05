package com.picpay.desafio.android.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.utils.Constants.BASE_URL
import com.picpay.desafio.android.utils.Constants.UNEXPECTED_ERROR
import com.picpay.desafio.android.utils.network.SafeCall
import com.picpay.desafio.android.utils.service.PicPayService
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserViewModel(private val service: PicPayService) : ViewModel() {
    private var _state: MutableLiveData<UserListState> = MutableLiveData()
    val state : LiveData<UserListState> = _state
    private var users : List<User>? = null



    fun getUsers() {
        viewModelScope.launch {
            if(users != null) {
                _state.value = UserListState.Success(users ?: listOf())
                return@launch
            }
            _state.value = UserListState.UserLoading(true)
            val response = SafeCall.getResponse { service.getUsers() }
            when(response?.code()){
                in 200..299 -> {
                    users = response?.body()
                    _state.value = UserListState.Success(users ?: listOf())
                }
                in 300..499 -> {
                    _state.value = UserListState.Error(response?.message() ?: UNEXPECTED_ERROR)
                }
                else ->{
                    _state.value = UserListState.Error(UNEXPECTED_ERROR)
                }
            }
            _state.value = UserListState.UserLoading(false)
        }
    }
}

sealed class UserListState {
    class UserLoading(val isLoading: Boolean) : UserListState()
    class Success(val users: List<User>) : UserListState()
    class Error(val message: String) : UserListState()
}

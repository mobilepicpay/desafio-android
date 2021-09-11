package com.picpay.desafio.android.presetation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.extensions.handleErrorApiMessage
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserServiceRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserViewModel(private val userServiceRepository: UserServiceRepository) : ViewModel() {

    data class UserState(
        val listUsers: List<User> = emptyList(),
        val error: Int? = null,
        val isLoading: Boolean = true
    )

    private val _users = MutableLiveData(UserState())
    val users: MutableLiveData<UserState> = _users

    fun getUsers() {
        viewModelScope.launch {
            userServiceRepository.getUsers()
                .catch { error ->
                    val apiException = error.handleErrorApiMessage()
                    _users.postValue(UserState(error = apiException, isLoading = false))
                }.collect { userList ->
                    _users.postValue(UserState(listUsers = userList, isLoading = false))
                }

        }
    }

}

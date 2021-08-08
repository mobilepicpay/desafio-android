package com.picpay.android.user.presentation

import androidx.lifecycle.*
import com.picpay.android.network.Error
import com.picpay.android.user.usedatasoucer.User
import com.picpay.android.user.usedatasoucer.local.UserLocalRepository
import com.picpay.android.user.usedatasoucer.network.UserNetWorkRepository
import com.picpay.android.util.ViewModelResponse
import kotlinx.coroutines.launch
import java.lang.Exception

class UserViewModel(
    private val userRepository: UserNetWorkRepository,
    private val userLocalRepository: UserLocalRepository
) : ViewModel(), LifecycleObserver {

    val userLiveData = MutableLiveData<ViewModelResponse<List<User>>>()
    private val listMemoryUsers = mutableListOf<User>()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun getUsers() {
        viewModelScope.launch {
            runCatching {
                userLiveData.value = ViewModelResponse.Loading(true)
                //Preserva a lista local para rotação de tela
                //para fazer o refresh da lista é preciso adicionar uma
                //ação de refresh ou iniciar novamente o app
                if (listMemoryUsers.isEmpty()) {
                    try {
                        listMemoryUsers.addAll(userRepository.getUsers())
                    } catch (e: Exception) {
                        //Se houver erro tenta recuperar a lista do bd local
                        listMemoryUsers.addAll(userLocalRepository.getUsers())
                        if (listMemoryUsers.isEmpty()) {
                            throw Error(
                                code = "List_empty",
                                errorMessage = "Houve um erro no servidor é não temos a lista salva no dispositivo"
                            )
                        }
                    }
                }
                listMemoryUsers
            }.onSuccess {
                userLocalRepository.insertUsers(it)
                userLiveData.value = ViewModelResponse.Success(it)
            }.onFailure {
                userLiveData.value = ViewModelResponse.Error(it)
            }

            userLiveData.value = ViewModelResponse.Loading(false)
        }
    }

    override fun onCleared() {
        super.onCleared()

    }

}
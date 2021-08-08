package com.picpay.android.user.presentation

import androidx.lifecycle.*
import com.picpay.android.network.CustomError
import com.picpay.android.user.usedatasoucer.User
import com.picpay.android.user.usedatasoucer.local.UserLocalRepository
import com.picpay.android.user.usedatasoucer.network.UserNetWorkRepository
import com.picpay.android.network.ViewModelResponse
import com.picpay.android.user.R
import kotlinx.coroutines.launch

/**
 * Preserva a lista em memoria(listMemoryUsers) para rotação de tela fazendo refresh apenas
 * na inicializacao(OnCrete) do app.
 * Se o caso de uso requer atualização da lista pelo server é preciso adicionar uma ação(botão/swipe).
 */
class UserViewModel(
    private val userNetworkRepository: UserNetWorkRepository,
    private val userLocalRepository: UserLocalRepository
) : ViewModel(), LifecycleObserver {

    val userLiveData = MutableLiveData<ViewModelResponse<List<User>>>()
    private val listMemoryUsers = mutableListOf<User>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun getUsers() {
        viewModelScope.launch {
            runCatching {
                userLiveData.value = ViewModelResponse.Loading(true)

                if (listMemoryUsers.isEmpty()) {
                    try {
                        listMemoryUsers.addAll(userNetworkRepository.getUsers())
                    } catch (e: Exception) {
                        //Se houver erro tenta recuperar a lista do repositorio local
                        listMemoryUsers.addAll(userLocalRepository.getUsers())
                        if (listMemoryUsers.isEmpty()) {
                            throw CustomError(
                                code = "list_empty",
                                errorMessageRes = R.string.error_empty_list
                            )
                        }
                    }
                }
                listMemoryUsers
            }.onSuccess {
                userLocalRepository.deleteUsers()
                userLocalRepository.insertUsers(it)
                userLiveData.value = ViewModelResponse.Success(it)
            }.onFailure {
                userLiveData.value = ViewModelResponse.Error(it as CustomError)
            }

            userLiveData.value = ViewModelResponse.Loading(false)
        }
    }

    override fun onCleared() {
        super.onCleared()
        listMemoryUsers.clear()
    }

}
package com.picpay.desafio.android.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.model.ContactModel
import com.picpay.desafio.android.domain.useCases.ListContactsUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

sealed class State<out T : Any> {
    object Loading : State<Nothing>()
    data class Success<out T : Any>(val result: T) : State<T>()
    data class Error(val error: Throwable) : State<Nothing>()
}

class ContactListViewModel(
    private val listContactsUseCase: ListContactsUseCase
) : ViewModel(), KoinComponent {
    //private val listContactsUseCase by inject<ListContactsUseCase>()
    var responseState: MutableLiveData<State<List<ContactModel>>> = MutableLiveData()
    var items: MutableLiveData<List<ContactModel>> = MutableLiveData()

    fun loadContacts() {
        viewModelScope.launch {
            listContactsUseCase.execute()
                .onStart {
                    responseState.postValue(State.Loading)
                }
                .catch {
                    with(Exception("Could not connect to PicPay API")) {
                        responseState.postValue(State.Error(this))
                    }
                }
                .collect {
                    items.postValue(it)
                    responseState.postValue(State.Success(it))
                }
        }
    }
}
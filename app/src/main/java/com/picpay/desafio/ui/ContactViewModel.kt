package com.picpay.desafio.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.models.ContactListResponse
import com.picpay.desafio.repository.ContactRepository
import com.picpay.desafio.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class ContactViewModel(
    app: Application,
    val contactRepository: ContactRepository
) : AndroidViewModel(app) {

    val contacts: MutableLiveData<Resource<ContactListResponse>> = MutableLiveData()
    var contactListPage = 1

    init {
        getContactList()
    }

    fun getContactList() = viewModelScope.launch {
            contacts.postValue(Resource.Loading())
        val response = contactRepository.getContacts()
        contacts.postValue(handleContactListResponse(response))
    }

    private fun handleContactListResponse(response: Response<ContactListResponse>) : Resource<ContactListResponse>{
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}
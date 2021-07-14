package com.picpay.desafio.android.shared.usecases

import com.picpay.desafio.android.shared.base.BaseUseCase
import com.picpay.desafio.android.shared.model.ResultWrapper
import com.picpay.desafio.android.shared.model.ViewUser
import com.picpay.desafio.android.shared.services.PicPayService

/**
 * @author Vitor Herrmann on 12/07/2021
 */
interface FetchContactsUseCase {
    suspend fun fetchContacts(): ResultWrapper<List<ViewUser>>
}

class FetchContactsUseCaseImpl(
    private val picPayService: PicPayService
) : BaseUseCase(), FetchContactsUseCase {

    override suspend fun fetchContacts() = request {
        picPayService.getUsers().map {
            ViewUser(
                imageUrl = it.img,
                name = it.name,
                username = it.username
            )
        }
    }
}

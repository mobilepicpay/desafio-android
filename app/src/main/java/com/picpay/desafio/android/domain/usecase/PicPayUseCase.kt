package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.data.PicPayRepository
import javax.inject.Inject

class PicPayUseCase @Inject constructor(private val repository: PicPayRepository) {
    operator fun invoke() = repository.getUsers()
}
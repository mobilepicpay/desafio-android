package com.picpay.desafio.android.core.data.mappers

import com.picpay.desafio.android.core.data.image.AppImageProcessor
import com.picpay.desafio.android.core.data.image.util.compressImageFromUrl
import com.picpay.desafio.android.core.database.model.UserEntity
import com.picpay.desafio.android.core.model.UserModel
import com.picpay.desafio.android.core.network.model.UserResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

suspend fun UserResponse.toDomainModel(
    processor: AppImageProcessor,
    ioDispatcher: CoroutineDispatcher
) =
    withContext(ioDispatcher) {
        return@withContext UserModel(
            externalId = id,
            name = name,
            username = username,
            imgBytes = compressImageFromUrl(img, processor, ioDispatcher)
        )
    }

suspend fun List<UserResponse>.toDomainModel(
    processor: AppImageProcessor,
    ioDispatcher: CoroutineDispatcher
): List<UserModel> =
    withContext(ioDispatcher) {

        val usersModel: MutableList<UserModel> = mutableListOf()
        this@toDomainModel.forEach {
            usersModel.add(it.toDomainModel(processor, ioDispatcher))
        }
        return@withContext usersModel
    }


fun UserModel.toEntity() =
    UserEntity(
        id = externalId,
        imgBytes = imgBytes,
        name = name,
        username = username
    )

fun UserEntity.toModel() =
    UserModel(
        externalId = id,
        imgBytes = imgBytes,
        name = name,
        username = username
    )
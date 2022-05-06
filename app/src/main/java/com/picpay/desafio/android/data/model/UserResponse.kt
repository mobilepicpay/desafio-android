package com.picpay.desafio.android.data.model

import com.google.gson.annotations.SerializedName
import com.picpay.desafio.android.core_network.models.MissingFieldException
import com.picpay.desafio.android.domain.models.User

internal data class UserResponse(
    @SerializedName("img") val img: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("username") val username: String?
)
/**
 * Maps UserResponse class to User class
 * @throws
 * */
internal fun UserResponse.mapToUser() = User(
    img = img.orEmpty(),
    name = name.orEmpty(),
    id = id ?: throw MissingFieldException("user Id"),
    username = username.orEmpty()
)

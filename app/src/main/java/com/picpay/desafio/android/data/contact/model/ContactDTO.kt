package com.picpay.desafio.android.data.contact.model

import com.google.gson.annotations.SerializedName
import com.picpay.desafio.android.domain.model.Contact

data class ContactDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("username") val username: String?,
    @SerializedName("img") val imageUrl: String?,
)

fun ContactDTO.toContact() = Contact(
    id = id,
    name = name ?: "",
    username = username ?: "",
    imageUrl = imageUrl ?: ""
)
package com.picpay.desafio.android.data.features.contacts.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.picpay.desafio.android.data.features.contacts.local.entities.database.ContactDb
import com.picpay.desafio.android.domain.features.contacts.model.Contact
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactRemote(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("username") val username: String?,
    @SerializedName("img") val image: String?
) : Parcelable

fun ContactRemote.toDomain() = Contact(
    id = id ?: 0,
    name = name.orEmpty(),
    username = username.orEmpty(),
    image = image.orEmpty()
)

fun List<ContactRemote>.toDomain() = this.map {
    it.toDomain()
}

fun ContactRemote.toDb() = ContactDb(
    id = id ?: 0,
    name = name.orEmpty(),
    username = username.orEmpty(),
    image = image.orEmpty()
)

fun List<ContactRemote>.toDb() = this.map {
    it.toDb()
}
package com.picpay.desafio.android.model.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.picpay.desafio.android.model.domain.UserEntity
import kotlinx.android.parcel.Parcelize

/*
* Como não foi especificado quais infos poderiam ser null e nem mesmo se o crash de null aconteceu
* nos dados o user, alterei para todas as infos exceto id, para null
* */
@Parcelize
data class User(
    @SerializedName("img") var img: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String?
) : Parcelable{

    fun convertToUserDomain() : UserEntity {
       return  UserEntity(
                id = id,
                name = name,
                username = username,
                img = img
            )
    }

}
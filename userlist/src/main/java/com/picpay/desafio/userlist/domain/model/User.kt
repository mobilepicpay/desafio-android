package com.picpay.desafio.userlist.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
data class User(
    @PrimaryKey
    @SerializedName("id") val id: Int,
    @SerializedName("img") val img: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("username") val username: String?
)
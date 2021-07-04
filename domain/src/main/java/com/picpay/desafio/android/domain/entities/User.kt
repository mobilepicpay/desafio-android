package com.picpay.desafio.android.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val img: String,
    val name: String,
    val id: Int,
    val username: String
) : Parcelable
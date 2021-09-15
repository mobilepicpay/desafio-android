package com.picpay.desafio.android.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: Int,
    val name: String,
    val username: String,
    val img: String
) : Parcelable

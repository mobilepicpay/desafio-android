package com.picpay.desafio.android.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val name: String,
    val username: String,
    val img: String
) : Parcelable

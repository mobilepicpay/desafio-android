package com.picpay.desafio.android.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val img: String,
    val name: String,
    val id: Int,
    val username: String
) : Parcelable
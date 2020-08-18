package com.picpay.desafio.android.user.model

import android.os.Parcelable

interface User : Parcelable {
    val img: String
    val name: String
    val id: Int
    val username: String
}
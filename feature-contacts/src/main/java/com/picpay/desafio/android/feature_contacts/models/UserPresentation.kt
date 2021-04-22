package com.picpay.desafio.android.feature_contacts.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserPresentation(
    val img: String,
    val name: String,
    val id: Int,
    val username: String
): Parcelable
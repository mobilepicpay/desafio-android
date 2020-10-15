package com.picpay.desafio.android.user.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class ResultUser<T>(
    var isSuccess: Boolean = true,
    var data: @RawValue T,
    var messageError: String = ""
) : Parcelable
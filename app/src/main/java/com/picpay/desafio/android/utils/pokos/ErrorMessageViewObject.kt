package com.picpay.desafio.android.utils.pokos

import androidx.annotation.StringRes

data class ErrorMessageViewObject(
    @StringRes val messageResId: Int,
    @StringRes val actionResId: Int?
)

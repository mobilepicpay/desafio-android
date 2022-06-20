package com.picpay.desafio.ui.theme.resourceprovider

import androidx.annotation.StringRes

interface StringResourceProvider {
    fun getString(
        @StringRes
        stringId: Int
    ): String
}
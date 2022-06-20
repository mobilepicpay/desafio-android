package com.picpay.desafio.ui.theme.resourceprovider

import android.content.Context

class StringResourceProviderImpl(private val context: Context) : StringResourceProvider {
    override fun getString(stringId: Int): String {
       return context.getString(stringId)
    }
}
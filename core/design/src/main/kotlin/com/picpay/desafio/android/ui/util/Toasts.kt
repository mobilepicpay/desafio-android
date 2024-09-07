package com.picpay.desafio.android.ui.util

import android.content.Context
import android.widget.Toast

fun showShortToast(textId: Int, context: Context) =
    Toast.makeText(context, context.getString(textId), Toast.LENGTH_SHORT)
        .show()

fun showLongToast(textId: Int, context: Context) =
    Toast.makeText(context, context.getString(textId), Toast.LENGTH_LONG)
        .show()
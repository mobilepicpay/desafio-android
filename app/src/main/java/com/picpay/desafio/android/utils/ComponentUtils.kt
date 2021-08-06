package com.picpay.desafio.android.utils

import android.content.Context
import android.widget.Toast

object ComponentUtils {
    fun showToast(context: Context, message: String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT)
            .show()
    }
}
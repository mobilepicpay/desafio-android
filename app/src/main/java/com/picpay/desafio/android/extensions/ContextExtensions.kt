package com.picpay.desafio.android.extensions

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.toLayoutInflater(): LayoutInflater = LayoutInflater.from(this)

fun Context.showToastLongText(text: String?) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun Context.showToastLongText(@StringRes resId: Int) {
    Toast.makeText(this, resId, Toast.LENGTH_LONG).show()
}

fun Context.showToastShortText(text: String?) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.showToastShortText(@StringRes resId: Int) {
    Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
}
package com.picpay.desafio.android.app

import android.view.View

fun Int.print(): String{
    return PicPayApplication.instance.getString(this)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}



package com.picpay.desafio.android.shared.extensions

import android.view.View

/**
 * @author Vitor Herrmann on 12/07/2021
 */

fun View.toVisible() {
    visibility = View.VISIBLE
}

fun View.toGone() {
    visibility = View.GONE
}

package com.picpay.desafio.android.common.util

import android.widget.ImageView
import com.picpay.desafio.android.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


fun ImageView.load(
    img: String,
    callback: Callback,
    errorImg: Int = R.drawable.ic_round_account_circle
) {
    Picasso.get()
        .load(img)
        .error(errorImg)
        .into(this, callback)
}
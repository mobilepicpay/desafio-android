package com.picpay.desafio.android.utils.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun ImageView.loadImage(
    image: String,
    @DrawableRes errorPlaceholder: Int,
    onSuccess: () -> Unit,
    onError: () -> Unit
) {
    Picasso.get()
        .load(image)
        .error(errorPlaceholder)
        .into(
            this,
            object : Callback {
                override fun onSuccess() {
                    onSuccess()
                }
                override fun onError(e: Exception?) {
                    onError()
                }
            }
        )
}

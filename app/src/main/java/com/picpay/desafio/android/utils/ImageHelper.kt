package com.picpay.desafio.android.utils

import android.widget.ImageView
import com.picpay.desafio.android.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlin.reflect.KFunction0

object ImageHelper {

    fun downloadImage(
        image: ImageView,
        url: String,
        onDownloadComplete: KFunction0<Unit>
    ){
        Picasso.get()
            .load(url)
            .error(R.drawable.ic_round_account_circle)
            .into(image, object : Callback {
                override fun onSuccess() {
                    onDownloadComplete()
                }

                override fun onError(e: Exception?) {
                    onDownloadComplete()
                }
            })
    }
}
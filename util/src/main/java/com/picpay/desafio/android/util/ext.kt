package com.picpay.desafio.android.util

import android.graphics.Bitmap
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.snackbar.Snackbar

fun errorView(view: View, msg: String) {
    Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).setBackgroundTint(Color.RED).show()
}

fun ImageView.loadImage(url: String, callBack: () -> Unit) {
    Glide.with(this.context)
        .asBitmap()
        .load(url)
        .listener(object : RequestListener<Bitmap> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                callBack.invoke()
                return false
            }
            override fun onResourceReady(
                resource: Bitmap?,
                model: Any?,
                target: Target<Bitmap>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                callBack.invoke()
                return false
            }
        }).into(this)
}
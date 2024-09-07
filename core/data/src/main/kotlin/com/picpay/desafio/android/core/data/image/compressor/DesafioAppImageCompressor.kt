package com.picpay.desafio.android.core.data.image.compressor

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.picpay.desafio.android.core.data.image.AppImageCompressor
import com.picpay.desafio.android.core.data.image.model.ImageSize
import com.picpay.desafio.android.core.data.image.util.DEFAULT_QUALITY
import com.picpay.desafio.android.core.data.image.util.toByteArray
import java.io.ByteArrayOutputStream

class DesafioAppImageCompressor: AppImageCompressor {
    override fun compressImage(imageData: ByteArray, quality: Int): Pair<ByteArray, ImageSize> {
        val bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
        val compressedByteArray = outputStream.toByteArray()
        return Pair(compressedByteArray, ImageSize(bitmap.width, bitmap.height))
    }

    override fun scaleImage(imageData: ByteArray, width: Int, height: Int, filter: Boolean): Pair<ByteArray, ImageSize> {
        val bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, filter)
        return compressImage(scaledBitmap.toByteArray(), DEFAULT_QUALITY)
    }
}
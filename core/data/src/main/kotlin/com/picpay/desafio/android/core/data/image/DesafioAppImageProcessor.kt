package com.picpay.desafio.android.core.data.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.picpay.desafio.android.core.data.image.compressor.DesafioAppImageCompressor
import com.picpay.desafio.android.core.data.image.decoder.DesafioAppImageDecoder
import com.picpay.desafio.android.core.data.image.model.ImageSize
import com.picpay.desafio.android.core.data.image.util.DEFAULT_QUALITY
import com.picpay.desafio.android.core.data.image.util.toByteArray
import java.io.InputStream

class DesafioAppImageProcessor(
    private val imageCompressor: DesafioAppImageCompressor,
    private val imageDecoder: DesafioAppImageDecoder
) : AppImageProcessor {

    override fun decodeStream(inputStream: InputStream): Pair<ByteArray, ImageSize> =
        imageDecoder.decodeStream(inputStream)

    override fun openStreamFromUrl(url: String): InputStream =
        imageDecoder.openStreamFromUrl(url)

    override fun compressImage(imageData: ByteArray, quality: Int): Pair<ByteArray, ImageSize> =
        imageCompressor.compressImage(imageData, quality)

    override fun scaleImage(imageData: ByteArray, width: Int, height: Int, filter: Boolean): Pair<ByteArray, ImageSize> {
        val bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, filter)
        return compressImage(scaledBitmap.toByteArray(), DEFAULT_QUALITY)
    }
}
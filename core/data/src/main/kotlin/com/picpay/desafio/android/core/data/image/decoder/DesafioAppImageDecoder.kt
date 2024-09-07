package com.picpay.desafio.android.core.data.image.decoder

import android.graphics.BitmapFactory
import com.picpay.desafio.android.core.data.image.AppImageDecoder
import com.picpay.desafio.android.core.data.image.model.ImageSize
import com.picpay.desafio.android.core.data.image.util.toByteArray
import java.io.InputStream
import java.net.URL

class DesafioAppImageDecoder: AppImageDecoder {
    override fun decodeStream(inputStream: InputStream): Pair<ByteArray, ImageSize> {
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val byteArray = bitmap.toByteArray()  // This method should be implemented as shown below.
        return Pair(byteArray, ImageSize(bitmap.width, bitmap.height))
    }

    override fun openStreamFromUrl(url: String): InputStream =
        URL(url).openStream()
}
package com.picpay.desafio.android.core.data.image.fake

import com.picpay.desafio.android.core.data.image.AppImageDecoder
import com.picpay.desafio.android.core.data.image.model.ImageSize
import java.io.InputStream

class FakeAppImageDecoder(
    private val decodeStreamResult: Pair<ByteArray, ImageSize>,
    private val streamResult: InputStream
): AppImageDecoder {
    override fun decodeStream(inputStream: InputStream): Pair<ByteArray, ImageSize> =
        decodeStreamResult

    override fun openStreamFromUrl(url: String): InputStream =
        streamResult
}
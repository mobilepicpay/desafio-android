package com.picpay.desafio.android.core.data.image.fake

import com.picpay.desafio.android.core.data.image.model.ImageSize
import java.io.ByteArrayInputStream
import java.io.InputStream

private val fakeOriginalImageData = ByteArray(2000) { it.toByte() }
private val fakeDecodedImageSize = ImageSize(width = 200, height = 200)
private val fakeCompressedImageData = ByteArray(800) { it.toByte() }
private val fakeCompressedImageSize = ImageSize(width = 100, height = 100)
private val fakeInputStream = ByteArrayInputStream(fakeOriginalImageData)

fun createFakeImageProcessor(
    decodeStreamResult: Pair<ByteArray, ImageSize> = Pair(fakeOriginalImageData, fakeDecodedImageSize),
    streamResult: InputStream = fakeInputStream,
    compressImageResult: Pair<ByteArray, ImageSize> = Pair(fakeCompressedImageData, fakeCompressedImageSize),
    scaleImageResult: Pair<ByteArray, ImageSize> = Pair(fakeCompressedImageData, fakeCompressedImageSize)
): FakeAppImageProcessor {
    val fakeImageDecoder = FakeAppImageDecoder(
        decodeStreamResult = decodeStreamResult,
        streamResult = streamResult
    )

    val fakeImageCompressor = FakeAppImageCompressor(
        compressImageResult = compressImageResult,
        scaleImageResult = scaleImageResult
    )

    return FakeAppImageProcessor(
        imageCompressor = fakeImageCompressor,
        imageDecoder = fakeImageDecoder
    )
}

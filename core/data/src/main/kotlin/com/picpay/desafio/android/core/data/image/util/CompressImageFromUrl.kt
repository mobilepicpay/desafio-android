package com.picpay.desafio.android.core.data.image.util

import com.picpay.desafio.android.core.data.image.AppImageProcessor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

const val DEFAULT_IMAGE_TARGET_SIZE = 100 // Width || Height
const val DEFAULT_MAX_BYTES_SIZE = 50
const val DEFAULT_QUALITY = 75

suspend fun compressImageFromUrl(
    imageUrl: String,
    imageProcessor: AppImageProcessor,
    ioDispatcher: CoroutineDispatcher,
    targetWidth: Int = DEFAULT_IMAGE_TARGET_SIZE,
    targetHeight: Int = DEFAULT_IMAGE_TARGET_SIZE,
    maxSizeInBytes: Int = DEFAULT_MAX_BYTES_SIZE
): ByteArray = withContext(ioDispatcher) {
    runCatching {
        val inputStream = imageProcessor.openStreamFromUrl(imageUrl)
        val (originalImageData, originalSize) = imageProcessor.decodeStream(inputStream)

        val resizeIsNeeded =
            originalSize.width > targetWidth || originalSize.height > targetHeight

        val scaledImageData = Pair(originalImageData, originalSize)
            .takeUnless { resizeIsNeeded }
            ?: imageProcessor
                .scaleImage(originalImageData, targetWidth, targetHeight, true)

        val compressedImageData = imageProcessor
            .compressImage(scaledImageData.first, DEFAULT_QUALITY)
            .first

        val itNeedFurtherCompression =
            compressedImageData.size > maxSizeInBytes

        compressedImageData
            .takeUnless { itNeedFurtherCompression }
            ?: compressImageFurther(compressedImageData, imageProcessor, ioDispatcher)

    }.getOrElse { exception ->
        exception.printStackTrace()
        byteArrayOf()
    }
}

private suspend fun compressImageFurther(
    imageData: ByteArray,
    processor: AppImageProcessor,
    ioDispatcher: CoroutineDispatcher,
    quality: Int = DEFAULT_QUALITY
): ByteArray = withContext(ioDispatcher) {
    val (compressedImageData, _) = processor.compressImage(imageData, quality)
    compressedImageData
}
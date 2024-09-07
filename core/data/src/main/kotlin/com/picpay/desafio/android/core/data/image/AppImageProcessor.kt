package com.picpay.desafio.android.core.data.image

import com.picpay.desafio.android.core.data.image.model.ImageSize
import java.io.InputStream

interface AppImageProcessor : AppImageCompressor, AppImageDecoder

interface AppImageDecoder {

    /**
     * Decodes an InputStream into an image representation.
     *
     * @param inputStream The InputStream to decode the image from.
     * @return A Pair containing the image data as ByteArray and its size.
     */
    fun decodeStream(inputStream: InputStream): Pair<ByteArray, ImageSize>


    /**
     * Opens an InputStream from a given URL.
     *
     * @param url The URL from which to open the InputStream.
     * @return The InputStream obtained from the URL.
     */
    fun openStreamFromUrl(url: String): InputStream
}

interface AppImageCompressor {
    /**
     * Compresses the provided image data and returns the compressed data and its size.
     *
     * @param imageData The image data to compress.
     * @param quality The quality of compression, typically ranging from 0 to 100.
     * @return A Pair containing the compressed byte array and its size.
     */
    fun compressImage(imageData: ByteArray, quality: Int): Pair<ByteArray, ImageSize>

    /**
     * Scales the provided image data to the specified width and height, and returns the scaled data and its size.
     *
     * @param imageData The image data to scale.
     * @param width The desired width of the scaled image.
     * @param height The desired height of the scaled image.
     * @param filter Whether to apply a filter during scaling.
     * @return A Pair containing the scaled byte array and its size.
     */
    fun scaleImage(
        imageData: ByteArray,
        width: Int,
        height: Int,
        filter: Boolean
    ): Pair<ByteArray, ImageSize>
}
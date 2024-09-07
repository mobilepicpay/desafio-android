package com.picpay.desafio.android.core.data.model

import com.picpay.desafio.android.core.data.di.test.testingDataModule
import com.picpay.desafio.android.core.data.image.fake.FakeAppImageCompressor
import com.picpay.desafio.android.core.data.image.fake.FakeAppImageDecoder
import com.picpay.desafio.android.core.data.image.fake.FakeAppImageProcessor
import com.picpay.desafio.android.core.data.image.model.ImageSize
import com.picpay.desafio.android.core.data.mappers.toDomainModel
import com.picpay.desafio.android.core.network.model.UserResponse
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import java.io.ByteArrayInputStream
import kotlin.test.assertContentEquals

class UserMapperTest: KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(testingDataModule)
    }

    private val testDispatcher: TestDispatcher by inject()

    private lateinit var fakeImageProcessor: FakeAppImageProcessor

    private val imageUrl = "https://example.of/false_url_test_image.jpg"
    private val fakeImgBytes = ByteArray(100) { it.toByte() } // Example byte array for image data


    @Before
    fun setUp() {
        val fakeOriginalImageData = ByteArray(2000) { it.toByte() }
        val fakeCompressedImageData = fakeImgBytes
        val fakeImageSize = ImageSize(width = 100, height = 100)
        val fakeInputStream = ByteArrayInputStream(fakeOriginalImageData)

        val fakeImageDecoder = FakeAppImageDecoder(
            decodeStreamResult = Pair(fakeOriginalImageData, fakeImageSize),
            streamResult = fakeInputStream
        )

        val fakeImageCompressor = FakeAppImageCompressor(
            compressImageResult = Pair(fakeCompressedImageData, fakeImageSize),
            scaleImageResult = Pair(fakeOriginalImageData, fakeImageSize)
        )

        fakeImageProcessor = FakeAppImageProcessor(
            imageCompressor = fakeImageCompressor,
            imageDecoder = fakeImageDecoder
        )
    }

    @Test
    fun `toDomainModel should convert UserResponse to UserModel correctly`() = runTest(testDispatcher) {
        // Given
        val userResponse = UserResponse(
            img = imageUrl,
            name = "John Doe",
            id = 123,
            username = "johndoe"
        )

        // When
        val result = userResponse.toDomainModel(fakeImageProcessor, testDispatcher)

        // Then
        assertEquals(userResponse.id, result.externalId)
        assertEquals(userResponse.name, result.name)
        assertEquals(userResponse.username, result.username)
        assertContentEquals(fakeImgBytes, result.imgBytes)
    }
}
package com.picpay.desafio.android.data.contact

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android._config.DataTest
import com.picpay.desafio.android._config.KoinModules.APIsModule
import com.picpay.desafio.android._config.KoinModules.mockRetrofitModule
import com.picpay.desafio.android._config.extension.enqueueResponse
import com.picpay.desafio.android._config.readFile
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.component.get
import org.koin.core.module.Module
import org.robolectric.annotation.Config
import retrofit2.HttpException

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE, sdk = [28])
class ContactRemoteDataSourceMockTest : DataTest() {

    override val koinModules: List<Module>
        get() = listOf(mockRetrofitModule, APIsModule)

    @Test
    fun `test fetch contacts and results match`() = runBlocking {
        // given
        val dataSource = ContactRemoteDataSource(get())
        mockWebServer.enqueueResponse(application.readFile("contact_list_200.json"), 200)
        // when
        val result = dataSource.fetchContacts()
        // then
        Assert.assertTrue(result.isSuccess)
        Assert.assertTrue(result.getOrThrow().size == 3)

        Assert.assertTrue(result.getOrThrow()[0].id == 1)
        Assert.assertTrue(result.getOrThrow()[0].name == "Sandrine Spinka")
        Assert.assertTrue(result.getOrThrow()[0].username == "Tod86")
        Assert.assertTrue(result.getOrThrow()[0].imageUrl == "https://randomuser.me/api/portraits/men/1.jpg")

        Assert.assertTrue(result.getOrThrow()[1].id == 2)
        Assert.assertTrue(result.getOrThrow()[1].name == "Carli Carroll")
        Assert.assertTrue(result.getOrThrow()[1].username == "Constantin_Sawayn")
        Assert.assertTrue(result.getOrThrow()[1].imageUrl == "https://randomuser.me/api/portraits/men/2.jpg")

        Assert.assertTrue(result.getOrThrow()[2].id == 3)
        Assert.assertTrue(result.getOrThrow()[2].name == null)
        Assert.assertTrue(result.getOrThrow()[2].username == "")
        Assert.assertTrue(result.getOrThrow()[2].imageUrl == "https://randomuser.me/api/portraits/men/3.jpg")
    }

    @Test
    fun `test fetch contacts with a failure result`() = runBlocking {
        // given
        val dataSource = ContactRemoteDataSource(get())
        mockWebServer.enqueueResponse("", 404)
        // when
        val result = dataSource.fetchContacts()
        // then
        Assert.assertTrue(result.isFailure)
        Assert.assertTrue(result.exceptionOrNull() is HttpException)
    }

}
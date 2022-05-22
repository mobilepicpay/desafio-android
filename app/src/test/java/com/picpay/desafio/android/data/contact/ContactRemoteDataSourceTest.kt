package com.picpay.desafio.android.data.contact

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android._config.DataTest
import com.picpay.desafio.android._config.KoinModules.APIsModule
import com.picpay.desafio.android._config.KoinModules.retrofitModule
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.component.get
import org.koin.core.module.Module
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE, sdk = [28])
class ContactRemoteDataSourceTest : DataTest() {

    override val koinModules: List<Module>
        get() = listOf(retrofitModule, APIsModule)

    @Test
    fun `test fetch contacts and receive a success result with a not empty list`() = runBlocking {
        // given
        val dataSource = ContactRemoteDataSource(get())
        // when
        val result = dataSource.fetchContacts()
        // then
        Assert.assertTrue(result.isSuccess)
        Assert.assertTrue(result.getOrThrow().isNotEmpty())
    }

}
package com.picpay.desafio.android.data.contact

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.picpay.desafio.android._config.DataTest
import com.picpay.desafio.android._config.readFile
import com.picpay.desafio.android.data.contact.model.ContactDTO
import com.picpay.desafio.android.domain.repository.ContactRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE, sdk = [28])
class ContactRepositoryTest : DataTest() {

    private val gson = Gson()

    @Test
    fun `test get contacts and receive a success result with a not empty list`() = runBlocking {
        // given
        val contactsDTO = gson.fromJson<List<ContactDTO>>(
            application.readFile("contact_list_200.json"),
            object : TypeToken<List<ContactDTO>>() {}.type
        )
        val contactRemoteDataSource = mock<ContactRemoteDataSource> {
            onBlocking { fetchContacts() } doReturn Result.success(contactsDTO)
        }
        val contactRepository: ContactRepository = ContactRepositoryImpl(contactRemoteDataSource)
        // when
        val result = contactRepository.getContacts()
        // then
        Assert.assertTrue(result.isSuccess)
        Assert.assertTrue(result.getOrThrow().isNotEmpty())
    }

}
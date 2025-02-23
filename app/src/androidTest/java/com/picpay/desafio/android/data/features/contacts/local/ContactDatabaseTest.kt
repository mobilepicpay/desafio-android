package com.picpay.desafio.android.data.features.contacts.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android.data.features.contacts.local.dao.ContactDao
import com.picpay.desafio.android.data.features.contacts.local.database.ContactDatabase
import com.picpay.desafio.android.data.features.contacts.local.entities.database.ContactDb
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ContactDatabaseTest {
    private lateinit var dbContacts: List<ContactDb>
    private lateinit var dao: ContactDao
    private lateinit var contactDatabase: ContactDatabase

    @Before
    fun createInstances() {
        val contact = ContactDb(
            id = 1,
            name = "Rodrigo M",
            username = "rodrigom",
            image = "https://example.com/rodrigom.jpg"
        )

        dbContacts = List(3) { index ->
            contact.copy(
                id = index + 1,
                name = "Rodrigo M ${index + 1}",
                username = "rodrigom${index + 1}",
                image = "https://example.com/rodrigom${index + 1}.jpg"
            )
        }
    }

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        contactDatabase = Room.inMemoryDatabaseBuilder(
            context,
            ContactDatabase::class.java
        ).build()

        dao = contactDatabase.dao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        contactDatabase.close()
    }

    @Test
    fun should_write_in_database() {
        lateinit var result: List<ContactDb>

        runBlocking {
            result = dao.queryContacts().first()
        }
        assertTrue(result.isEmpty())

        runBlocking {
            dao.insertAll(dbContacts)
            result = dao.queryContacts().first()
        }
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun should_read_from_database() {
        lateinit var result: ContactDb

        runBlocking {
            dao.deleteAll()

            dao.insertAll(dbContacts.subList(0, 1))
            result = dao.queryContacts().first()[0]
        }

        assertEquals(result.id, dbContacts[0].id)
    }

    @Test
    fun should_clear_database() {
        lateinit var result: List<ContactDb>

        runBlocking {
            dao.insertAll(dbContacts)
            dao.deleteAll()
            result = dao.queryContacts().first()
        }
        assertTrue(result.isEmpty())
    }
}
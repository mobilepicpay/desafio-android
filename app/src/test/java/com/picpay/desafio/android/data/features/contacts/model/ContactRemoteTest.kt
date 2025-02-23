package com.picpay.desafio.android.data.features.contacts.model

import org.junit.Assert.assertEquals
import org.junit.Test

class ContactRemoteTest {

    @Test
    fun `toDomain should map ContactRemote to Contact with non-null values`() {
        // Given
        val contactRemote = ContactRemote(
            id = 1,
            name = "Rodrigo M",
            username = "rodrigom",
            image = "https://example.com/rodrigom.jpg"
        )

        // When
        val contact = contactRemote.toDomain()

        // Then
        assertEquals(1, contact.id)
        assertEquals("Rodrigo M", contact.name)
        assertEquals("rodrigom", contact.username)
        assertEquals("https://example.com/rodrigom.jpg", contact.image)
    }

    @Test
    fun `toDomain should map ContactRemote to Contact with null values`() {
        // Given
        val contactRemote = ContactRemote(
            id = null,
            name = null,
            username = null,
            image = null
        )

        // When
        val contact = contactRemote.toDomain()

        // Then
        assertEquals(0, contact.id)
        assertEquals("", contact.name)
        assertEquals("", contact.username)
        assertEquals("", contact.image)
    }

    @Test
    fun `toDomain should map list of ContactRemote to list of Contact`() {
        // Given
        val contactRemoteList = listOf(
            ContactRemote(1, "Rodrigo M", "rodrigom", "https://example.com/rodrigom.jpg"),
            ContactRemote(2, "Rodrigo MM", "rodrigomm", "https://example.com/rodrigomm.jpg"),
            ContactRemote(null, null, null, null)
        )

        // When
        val contactList = contactRemoteList.toDomain()

        // Then
        assertEquals(3, contactList.size)

        assertEquals(1, contactList[0].id)
        assertEquals("Rodrigo M", contactList[0].name)
        assertEquals("rodrigom", contactList[0].username)
        assertEquals("https://example.com/rodrigom.jpg", contactList[0].image)

        assertEquals(2, contactList[1].id)
        assertEquals("Rodrigo MM", contactList[1].name)
        assertEquals("rodrigomm", contactList[1].username)
        assertEquals("https://example.com/rodrigomm.jpg", contactList[1].image)

        assertEquals(0, contactList[2].id)
        assertEquals("", contactList[2].name)
        assertEquals("", contactList[2].username)
        assertEquals("", contactList[2].image)
    }
}
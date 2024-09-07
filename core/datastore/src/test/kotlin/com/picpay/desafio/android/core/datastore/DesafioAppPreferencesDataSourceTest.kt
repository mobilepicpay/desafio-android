package com.picpay.desafio.android.core.datastore

import com.picpay.desafio.android.core.datastore.test.FakePreferencesDataStore
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class DesafioAppPreferencesDataSourceTest {

    private lateinit var repository: DataStoreRepository
    private lateinit var dataSource: PreferencesDataSource
    private lateinit var testString: String

    @Before
    fun setUp() {

        repository = FakePreferencesDataStore()

        dataSource = DesafioAppPreferencesDataSource(
            repository = repository
        )

        testString = "Test_String"
    }

    @Test
    fun `preferences should save and retrieves a string`() = runTest {
        // Given
        dataSource.saveSyncHash(testString)

        // When
        val hash = dataSource.getSyncHash()

        // Then
        assertEquals(testString, hash)
    }

    @Test
    fun `preferences should handle empty string`() = runTest {
        // Given
        val emptyString = ""
        dataSource.saveSyncHash(emptyString)

        // When
        val hash = dataSource.getSyncHash()

        // Then
        assertEquals(emptyString, hash)
    }

    @Test
    fun `preferences should handle null values`() = runTest {
        // When
        val hash = dataSource.getSyncHash()

        // Then
        assertNull(hash)
    }
}
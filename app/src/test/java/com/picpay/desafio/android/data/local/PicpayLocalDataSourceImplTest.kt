package com.picpay.desafio.android.data.local

import android.content.SharedPreferences
import com.google.common.truth.Truth
import com.picpay.desafio.android.coreNetwork.models.Response
import com.picpay.desafio.android.data.datasource.PicpayLocalDataSource
import com.picpay.desafio.android.data.model.UserEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class PicpayLocalDataSourceImplTest {

    private lateinit var dataSource: PicpayLocalDataSource
    private val sharedPrefs: SharedPreferences = mockk()
    private val database: PicpayDataBase = mockk()

    @Before
    fun setUp() {
        dataSource = PicpayLocalDataSourceImpl(
            database = database,
            sharedPreferences = sharedPrefs
        )
    }

    @Test
    fun `should return list of users`() = runBlocking {
        coEvery { database.PicpayDao().getUsers() } returns listOf(
            UserEntity("", "", 1, "")
        )

        dataSource.getUsers().first()

        coVerify(exactly = 1) { database.PicpayDao().getUsers() }

    }

    @Test
    fun `should return error if cached users is empty`() = runBlocking {
        coEvery { database.PicpayDao().getUsers() } returns listOf()

        val response = dataSource.getUsers().first()

        coVerify(exactly = 1) { database.PicpayDao().getUsers() }

        Truth.assertThat(response).isInstanceOf(Response.Error::class.java)
    }

    @Test
    fun `should save users and update cache time`() = runBlocking {
        coEvery { database.PicpayDao().insertUsers(any()) } returns Unit
        coEvery { sharedPrefs.edit().putLong(any(), any()).apply() } returns Unit

        dataSource.saveUsers(listOf(UserEntity("", "", 1, "")))

        coVerify(exactly = 1) { database.PicpayDao().insertUsers(any()) }
        coVerify(exactly = 1) { sharedPrefs.edit().putLong(any(), any()).apply() }
    }

}
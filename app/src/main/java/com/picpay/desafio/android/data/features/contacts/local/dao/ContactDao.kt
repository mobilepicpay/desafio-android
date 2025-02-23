package com.picpay.desafio.android.data.features.contacts.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.data.features.contacts.local.entities.database.ContactDb
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<ContactDb>)

    @Query("SELECT * FROM contact")
    fun queryContacts(): Flow<List<ContactDb>>

    @Query("DELETE FROM contact")
    suspend fun deleteAll()
}
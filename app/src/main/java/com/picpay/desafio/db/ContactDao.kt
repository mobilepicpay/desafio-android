package com.picpay.desafio.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.picpay.desafio.models.Contact

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: Contact): Long

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): LiveData<List<Contact>>

    @Delete
    suspend fun deleteContact(contact: Contact)
}
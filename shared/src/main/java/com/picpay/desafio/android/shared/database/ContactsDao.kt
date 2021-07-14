package com.picpay.desafio.android.shared.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.shared.model.ViewUser

/**
 * @author Vitor Herrmann on 13/07/2021
 */

@Dao
interface ContactsDao {

    @Query("SELECT * FROM ViewUser")
    fun getAll(): List<ViewUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<ViewUser>)

}

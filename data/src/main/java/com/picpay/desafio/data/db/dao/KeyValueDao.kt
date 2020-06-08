package com.picpay.desafio.data.db.dao

import androidx.annotation.NonNull
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.data.db.models.KeyValuePair

@Dao
interface KeyValueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(keyValueData: KeyValuePair)

    @Query("SELECT * FROM KeyValuePair WHERE `key` = :key LIMIT 1")
    suspend fun get(@NonNull key: String): KeyValuePair?

    @Query("DELETE FROM KeyValuePair WHERE `key` = :key")
    suspend fun delete(@NonNull key: String)

    @Query("DELETE FROM KeyValuePair")
    suspend fun clear()

    @Query("SELECT * FROM KeyValuePair")
    suspend fun getAll(): List<KeyValuePair>?

}
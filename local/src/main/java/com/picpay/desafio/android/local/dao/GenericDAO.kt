package com.picpay.desafio.android.local.dao

import androidx.room.*

@Dao
interface GenericDAO<Entity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(value: Entity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(values : List<Entity>)

    @Update
    suspend fun update(value: Entity)

    @Delete
    suspend fun delete(value: Entity)
}
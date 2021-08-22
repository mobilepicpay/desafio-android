package com.picpay.desafio.android.local.dao

import androidx.room.*

@Dao
interface GenericDAO<Entity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(value: Entity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(values : List<Entity>)

    @Update
    fun update(value: Entity)

    @Delete
    fun delete(value: Entity)
}
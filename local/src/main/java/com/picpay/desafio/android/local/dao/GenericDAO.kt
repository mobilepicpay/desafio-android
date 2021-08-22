package com.picpay.desafio.android.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface GenericDAO<Entity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(value: Entity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(values : List<Entity>) : Long

    @Update
    fun update(value: Entity)

    @Update
    fun delete(value: Entity)
}
package com.picpay.desafio.android.shared.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PicPayLocalDataSource {

    @Query("SELECT * FROM UserLocal")
    suspend fun getUserList(): List<UserLocal>

    @Insert
    suspend fun insertAll(list: List<UserLocal>)
}
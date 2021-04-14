package com.picpay.desafio.android.shared.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserLocal::class], version = 1, exportSchema = false)
abstract class PicPayDataBase : RoomDatabase() {
    abstract fun localDataSource(): PicPayLocalDataSource
}

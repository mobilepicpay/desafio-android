package com.picpay.desafio.android.shared.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserLocal::class], version = 1)
abstract class PicPayDataBase : RoomDatabase() {
    abstract fun localDataSource(): PicPayLocalDataSource
}

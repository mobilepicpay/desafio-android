package com.picpay.desafio.android.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.model.UserEntity

@Database(entities = [UserEntity::class], version = 1)
internal abstract class PicpayDataBase : RoomDatabase() {

    abstract fun PicpayDao(): PicpayDao

}

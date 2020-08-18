package com.picpay.desafio.android.user.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.user.database.constants.DataBaseConstants
import com.picpay.desafio.android.user.database.dao.UserDAO
import com.picpay.desafio.android.user.database.entity.UserEntity

@Database(
    version = DataBaseConstants.DATABASE_VERSION,
    entities = [UserEntity::class],
    exportSchema = false
)
abstract class UserDataBase : RoomDatabase() {
    abstract fun userDAO(): UserDAO
}
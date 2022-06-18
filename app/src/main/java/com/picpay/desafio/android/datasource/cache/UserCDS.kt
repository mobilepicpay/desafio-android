package com.picpay.desafio.android.datasource.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.datasource.cache.model.UserCM

@Database(entities = [UserCM::class], version = 1, exportSchema = false)
abstract class UserCDS: RoomDatabase() {

    abstract val userDAO: UserDAO

    companion object{
        private const val USER_DATABASE_NAME = "user_database"


        @Volatile
        private var INSTANCE: UserCDS? = null

        fun getInstance(context: Context): UserCDS{

            synchronized(this){
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserCDS::class.java,
                        USER_DATABASE_NAME,
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }

                return instance
            }
        }


    }
}
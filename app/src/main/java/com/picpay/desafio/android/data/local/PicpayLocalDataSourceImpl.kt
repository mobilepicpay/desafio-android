package com.picpay.desafio.android.data.local

import android.content.SharedPreferences
import com.picpay.desafio.android.coreNetwork.models.NetworkError
import com.picpay.desafio.android.coreNetwork.models.Response
import com.picpay.desafio.android.data.datasource.PicpayLocalDataSource
import com.picpay.desafio.android.data.local.LocalConfigs.KEY_CACHE_TIME
import com.picpay.desafio.android.data.model.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

private const val FIVE_SECONDS_MILLIS = 300000

internal class PicpayLocalDataSourceImpl @Inject constructor(
    private val database: PicpayDataBase,
    private val sharedPreferences: SharedPreferences
) : PicpayLocalDataSource {

    override suspend fun getUsers(): Flow<Response<List<UserEntity>>> {
        return flow {
            val cache = database.PicpayDao().getUsers()

            if (cache.isEmpty()) {
                emit(
                    Response.Error(
                        NetworkError(
                            errorMessage = "Cache not available",
                            errorCode = 500
                        )
                    )
                )
            } else {
                emit(Response.Success(cache))
            }
        }
    }

    override suspend fun saveUsers(users: List<UserEntity>) {
        sharedPreferences.edit().putLong(KEY_CACHE_TIME, Calendar.getInstance().timeInMillis)
            .apply()
        database.PicpayDao().insertUsers(users)
    }

    override fun checkCacheExpired(): Boolean {
        val lastCacheMillis = sharedPreferences.getLong(KEY_CACHE_TIME, 0)
        if (lastCacheMillis == 0L) return true

        val lastCacheDate = GregorianCalendar()
        lastCacheDate.timeInMillis = lastCacheMillis

        val currentDate = GregorianCalendar()

        return lastCacheDate.timeInMillis + FIVE_SECONDS_MILLIS < currentDate.timeInMillis
    }

}

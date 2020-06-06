package com.picpay.desafio.android.features.user.repository

import com.picpay.desafio.android.base.OnError
import com.picpay.desafio.android.base.OnProgress
import com.picpay.desafio.android.base.OnSuccess
import com.picpay.desafio.android.custom.aliases.UserResult
import com.picpay.desafio.android.custom.aliases.UsersResult
import com.picpay.desafio.android.custom.errors.ErrorMessages
import com.picpay.desafio.android.custom.errors.NetworkException
import com.picpay.desafio.android.custom.errors.NoDataException
import com.picpay.desafio.android.mappers.user.UserMapper
import com.picpay.desafio.android.network.api.PicPayService
import com.picpay.desafio.android.room.dao.StringKeyValueDao
import com.picpay.desafio.android.room.dao.UserDao
import com.picpay.desafio.android.utils.Constants
import com.picpay.desafio.android.utils.Utils
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import timber.log.Timber
import java.io.IOException

class UserRepository(
    private val userDao: UserDao,
    private val picPayService: PicPayService,
    private val stringKeyValueDao: StringKeyValueDao
) {

    suspend fun getUser(userId: Int): Flow<UserResult> = flow {
        userDao.getUser(userId)?.let {
            emit(OnSuccess(it))
        } ?: emit(OnError(NoDataException()))
    }

    suspend fun getUsers(): Flow<UsersResult> = flow {
        stringKeyValueDao.get(Constants.LAST_USER_API_CALL_TIMESTAMP)
            ?.takeIf { !Utils.shouldCallApi(it.value, Constants.CACHE_THRESHOLD) }
            ?.let { emit(getDataOrError(NoDataException())) }
            ?: emit(getUserFromAPI())
    }
        .retryWhen { cause, attempt ->
            when {
                (cause is IOException && attempt < Constants.MAX_RETRIES) -> {
                    delay(Constants.RETRY_DELAY)
                    true
                }
                else -> {
                    false
                }
            }
        }
        .onStart { emit(OnProgress(loading = true)) }
        .onCompletion { emit(OnProgress(loading = false)) }
        .catch {
            emit(getDataOrError(NoDataException()))
        }

    private suspend fun getUserFromAPI() =
        picPayService.getUsers().run {
            Timber.d("Get users from API")
            if (isSuccessful) {
                body()?.let {
                    stringKeyValueDao.insert(
                        Utils.getCurrentTimeKeyValuePair(Constants.LAST_USER_API_CALL_TIMESTAMP)
                    )
                    userDao.deleteAllAndInsert(UserMapper(it).map())
                    getDataOrError(NoDataException())
                } ?: OnError(NoDataException(ErrorMessages.EMPTY_RESPONSE))
            } else {
                OnError(NetworkException())
            }
        }

    private suspend fun getDataOrError(throwable: Throwable) =
        userDao.get()?.let { userList ->
            Timber.d("Get users from DATABSE")
            OnSuccess(userList)
        } ?: OnError(throwable)
}
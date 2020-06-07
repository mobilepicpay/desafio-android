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
import com.picpay.desafio.android.features.user.network.api.UserService
import com.picpay.desafio.android.room.dao.KeyValueDao
import com.picpay.desafio.android.room.dao.UserDao
import com.picpay.desafio.android.utils.Constants
import com.picpay.desafio.android.utils.Utils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import timber.log.Timber

class UserRepository(
    private val userDao: UserDao,
    private val keyValueDao: KeyValueDao,
    private val userService: UserService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getUser(userId: Int): Flow<UserResult> = flow {
        userDao.getUser(userId)?.let { user ->
            emit(OnSuccess(user))
        } ?: emit(OnError(NoDataException()))
    }

    suspend fun getUsers(): Flow<UsersResult> = flow {
        keyValueDao.get(Constants.LAST_USER_API_CALL_TIMESTAMP)
            ?.takeIf { !Utils.shouldCallApi(it.value, Constants.CACHE_THRESHOLD) }
            ?.let { emit(getDataOrError(NoDataException())) }
            ?: emit(getUserFromAPI())
    }
        .retry(Constants.MAX_RETRIES) { e ->
            (e is Exception).also {
                if (it) delay(Constants.RETRY_DELAY)
            }
        }
        .onStart { emit(OnProgress(loading = true)) }
        .onCompletion { emit(OnProgress(loading = false)) }
        .catch {
            emit(getDataOrError(NoDataException()))
        }
        .flowOn(dispatcher)

    private suspend fun getUserFromAPI() =
        userService.getUsers().run {
            Timber.d("Get users from API")
            if (isSuccessful) {
                body()?.let { usersResponse ->
                    keyValueDao.insert(
                        Utils.getCurrentTimeKeyValuePair(Constants.LAST_USER_API_CALL_TIMESTAMP)
                    )
                    val userList = UserMapper(usersResponse).map()
                    userDao.deleteAllAndInsert(userList)
                    if (userList.isNotEmpty()) {
                        OnSuccess(userList)
                    } else {
                        OnError(NoDataException())
                    }
                } ?: OnError(NoDataException(ErrorMessages.EMPTY_RESPONSE))
            } else {
                OnError(NetworkException())
            }
        }

    private suspend fun getDataOrError(throwable: Throwable) =
        userDao.getUsers()?.let { userList ->
            Timber.d("Get users from DATABASE")
            OnSuccess(userList)
        } ?: OnError(throwable)
}
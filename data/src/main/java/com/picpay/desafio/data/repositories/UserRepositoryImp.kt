package com.picpay.desafio.data.repositories

import com.picpay.desafio.data.exceptions.ErrorMessages
import com.picpay.desafio.data.exceptions.NetworkException
import com.picpay.desafio.data.exceptions.NoDataException
import com.picpay.desafio.data.db.dao.KeyValueDao
import com.picpay.desafio.data.db.dao.UserDao
import com.picpay.desafio.data.utils.Constants
import com.picpay.desafio.data.utils.DataHelper
import com.picpay.desafio.data.apiservice.UserService
import com.picpay.desafio.data.mappers.UserDataToUserMapper
import com.picpay.desafio.data.mappers.UserResponseToUserDataMapper
import com.picpay.desafio.data.mappers.UserResponseToUserMapper
import com.picpay.desafio.domain.models.User
import com.picpay.desafio.domain.repositories.UserRepository
import com.picpay.desafio.domain.result.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class UserRepositoryImp (
    private val userDao: UserDao,
    private val keyValueDao: KeyValueDao,
    private val userService: UserService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserRepository{

    override suspend fun getUser(userId: Int): Flow<Result<User>> = flow {
        userDao.getUser(userId)?.let { userData ->
            val user = UserDataToUserMapper(arrayListOf(userData)).map()[0]
            emit(OnSuccess(user))
        } ?: emit(OnError(NoDataException()))
    }

    override suspend fun getUsers(): Flow<Result<List<User>>> = flow {
        keyValueDao.get(Constants.LAST_USER_API_CALL_TIMESTAMP)
            ?.takeIf { !DataHelper.shouldCallApi(it.value, Constants.CACHE_THRESHOLD) }
            ?.let { emit(getDataOrError(NoDataException())) }
            ?: emit(getUserFromAPI())
         }
        .retry(Constants.MAX_RETRIES) { e ->
            (e is Exception).also {
                if (it) delay(Constants.RETRY_DELAY)
            }
        }
        .onStart { emit(OnLoading) }
        .onCompletion { emit(OnComplete) }
        .catch {
            emit(getDataOrError(NoDataException()))
        }
        .flowOn(dispatcher)

    private suspend fun getUserFromAPI() =
        userService.getUsers().run {
            if (isSuccessful) {
                body()?.let { usersResponse ->
                    keyValueDao.insert(
                        DataHelper.getCurrentTimeKeyValuePair(Constants.LAST_USER_API_CALL_TIMESTAMP)
                    )

                    userDao.deleteAllAndInsert(UserResponseToUserDataMapper(usersResponse).map())

                    val users = UserResponseToUserMapper(usersResponse).map()

                    if (users.isNotEmpty()) {
                        OnSuccess(users)
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
            OnSuccess(UserDataToUserMapper(userList).map())
        } ?: OnError(throwable)

}
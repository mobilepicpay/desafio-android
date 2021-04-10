package com.picpay.desafio.android.feature.home.interactor.user

import com.picpay.desafio.android.shared.coroutine.CoroutineDispatching
import com.picpay.desafio.android.shared.data.local.PicPayLocalDataSource
import com.picpay.desafio.android.shared.data.local.UserLocal
import com.picpay.desafio.android.shared.data.remote.PicPayRemoteDataSource
import com.picpay.desafio.android.shared.data.remote.UserRemote
import com.picpay.desafio.android.shared.exception.NoInternetException
import com.picpay.desafio.android.shared.exception.UnknowServerException
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.UnknownHostException

class DefaultUserGateway(
    private val dispatching: CoroutineDispatching,
    private val remote: PicPayRemoteDataSource,
    private val local: PicPayLocalDataSource
) : UserGateway {

    override suspend fun getUserList(): List<UserEntity> = withContext(dispatching.IO) {
        val users = local.getUserList()
        if (users.isNotEmpty()) {
            return@withContext users.toEntity()
        } else {
            try {
                return@withContext remote.getUserList().toEntity().also {
                    local.insertAll(it.toLocal())
                }
            } catch (exception: UnknownHostException) {
                throw NoInternetException()
            } catch (exception: HttpException) {
                throw UnknowServerException()
            }
        }
    }

    @JvmName("toEntityUserRemote")
    private fun List<UserRemote>.toEntity(): List<UserEntity> {
        return map { UserEntity(it.id, it.username, it.name, it.img) }
    }

    @JvmName("toEntityUserLocal")
    private fun List<UserLocal>.toEntity(): List<UserEntity> {
        return map { UserEntity(it.id, it.username, it.name, it.imageUrl) }
    }

    private fun List<UserEntity>.toLocal(): List<UserLocal> {
        return map { UserLocal(it.id, it.username, it.name, it.imageUrl) }
    }
}

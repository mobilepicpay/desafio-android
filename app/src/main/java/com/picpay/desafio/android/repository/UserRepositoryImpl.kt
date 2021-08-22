package com.picpay.desafio.android.repository

import androidx.room.withTransaction
import com.picpay.desafio.android.local.config.DataBase
import com.picpay.desafio.android.model.UserDTO
import com.picpay.desafio.android.model.entity.toUserDTO
import com.picpay.desafio.android.model.toUserEntity
import com.picpay.desafio.android.network.config.NetworkConfig
import com.picpay.desafio.android.service.UserService
import com.picpay.desafio.android.util.Resource
import com.picpay.desafio.android.util.networkBoundResource
import kotlinx.coroutines.flow.*

class UserRepositoryImpl(
    private val networkConfig: NetworkConfig,
    private val service: UserService,
    private val db: DataBase
) : UserRepository {

    override fun getUsers(): Flow<Resource<List<UserDTO>>> {
        return networkBoundResource(
            query = { db.userDAO().getUsers().map { list -> list.map { it.toUserDTO() } } },
            fetch = { networkConfig.callService { service.getUsers() } },
            saveFetchResult = {
                db.withTransaction {
                    db.userDAO().deleteAll()
                    db.userDAO().saveAll(it.map { it.toUserEntity() })
                }
            },
            { true }
        )
    }
}
package com.picpay.desafio.android.local.mapper

import com.picpay.desafio.android.entities.UsersDomain
import com.picpay.desafio.android.local.modal.UsersDataCache

object UsersCacheMapper {
    fun map(cacheData: List<UsersDataCache>) = cacheData.map { map(it) }

    private fun map(cacheData: UsersDataCache) = UsersDomain(
        img = cacheData.img,
        name = cacheData.name,
        id = cacheData.id,
        username = cacheData.username
    )

    fun mapJobsToCache(users: List<UsersDomain>) = users.map { map(it) }

    fun map(data: UsersDomain) = UsersDataCache(
        img = data.img,
        name = data.name,
        id = data.id,
        username = data.username
    )
}
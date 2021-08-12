package com.picpay.desafio.android.data.db.mappers

import com.picpay.desafio.android.data.db.entities.UserEntity
import com.picpay.desafio.android.models.User
import javax.inject.Inject

class UserLocalMapper @Inject constructor() : LocalMapper<UserEntity, User> {

    override fun fromModelToEntity(model: User): UserEntity {
        return model.run {
            UserEntity(id, name, image, username)
        }
    }

    override fun fromEntityToModel(entity: UserEntity): User {
        return entity.run {
            User(id, name, image, username)
        }
    }
}

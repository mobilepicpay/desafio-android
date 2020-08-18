package com.picpay.desafio.android.user.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.picpay.desafio.android.user.model.User
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "User")
@Parcelize
data class UserEntity(
    override val img: String,
    override val name: String,
    @PrimaryKey
    override val id: Int,
    override val username: String
) : User
package com.picpay.desafio.android.user.repository.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "User")
@Parcelize
data class UserEntity(
    @PrimaryKey val id: Int,
    val img: String,
    val name: String,
    val username: String
) : Parcelable
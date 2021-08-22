package com.picpay.desafio.android.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class UserEntity (
    val img: String,
    val name: String,
    @PrimaryKey val id: Int,
    val username: String
) : Parcelable
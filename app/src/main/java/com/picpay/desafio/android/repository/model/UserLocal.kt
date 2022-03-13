package com.picpay.desafio.android.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserLocal(val img: String?,
                @PrimaryKey
                val id: Int?,
                val name: String?,
                val username: String?)
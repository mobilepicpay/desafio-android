package com.picpay.desafio.android.shared.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Vitor Herrmann on 13/07/2021
 */

@Entity
data class ViewUser(
    val imageUrl: String,
    val name: String,
    @PrimaryKey val username: String
)

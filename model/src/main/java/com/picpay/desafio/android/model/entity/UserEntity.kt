package com.picpay.desafio.android.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.picpay.desafio.android.model.UserDTO
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class UserEntity (
    val img: String,
    val name: String,
    @PrimaryKey val id: Int,
    val username: String
) : Parcelable

fun UserEntity.toUserDTO() : UserDTO {
    return with(this) {
        UserDTO(
            img = this.img,
            name = this.name,
            id = this.id,
            username = this.username
        )
    }
}
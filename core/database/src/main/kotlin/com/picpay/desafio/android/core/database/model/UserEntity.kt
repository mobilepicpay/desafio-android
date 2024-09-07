package com.picpay.desafio.android.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_tbl",
    indices = [
        Index(value = ["name"]),
        Index(value = ["username"])
    ])
data class UserEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val imgBytes: ByteArray,
    val name: String,
    val username: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserEntity

        if (id != other.id) return false
        if (!imgBytes.contentEquals(other.imgBytes)) return false
        if (name != other.name) return false
        if (username != other.username) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + imgBytes.contentHashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + username.hashCode()
        return result
    }
}

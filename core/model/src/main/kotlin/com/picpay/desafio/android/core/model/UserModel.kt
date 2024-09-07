package com.picpay.desafio.android.core.model

data class UserModel(
    val imgBytes: ByteArray,
    val name: String,
    val externalId: Int,
    val username: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserModel

        if (!imgBytes.contentEquals(other.imgBytes)) return false
        if (name != other.name) return false
        if (externalId != other.externalId) return false
        if (username != other.username) return false

        return true
    }

    override fun hashCode(): Int {
        var result = imgBytes.contentHashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + externalId
        result = 31 * result + username.hashCode()
        return result
    }
}
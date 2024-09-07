package com.picpay.desafio.android.core.data.sync

import com.picpay.desafio.android.core.network.model.UserResponse
import java.security.MessageDigest

class DataSyncManager {

    private fun hashWithMd5(bytes: ByteArray): String =
        MessageDigest.getInstance("MD5")
            .digest(bytes)
            .joinToString("") { "%02x".format(it) }


    fun hashUserListWithMd5(userList: List<UserResponse>): String =
        userList
            .sortedBy { it.id }
            .run { joinToString { user -> "${user.id},${user.name},${user.username},${user.img}" } }
            .let { hashWithMd5(it.toByteArray()) }
}
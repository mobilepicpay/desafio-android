package com.picpay.desafio.android.resources

import com.picpay.desafio.android.data.models.UserResponse
import com.picpay.desafio.android.domain.entities.UserEntity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

object UserResources {

    val emptyListUserEntity = listOf<UserEntity>()
    val dummyListUserEntity = listOf(
        UserEntity("", "baseName", 1, "baseUserName"),
        UserEntity("", "baseName", 2, "baseUserName"),
        UserEntity("", "baseName", 3, "baseUserName"),
        UserEntity("", "baseName", 4, "baseUserName"),
        UserEntity("", "baseName", 5, "baseUserName")
    )

    private val emptyListUserResponse = listOf<UserResponse>()
    private val dummyListUserResponse = listOf(
        UserResponse(1, "baseName", "baseUserName", ""),
        UserResponse(2, "baseName", "baseUserName", ""),
        UserResponse(3, "baseName", "baseUserName", ""),
        UserResponse(4, "baseName", "baseUserName", ""),
        UserResponse(5, "baseName", "baseUserName", "")
    )

    val apiResponseSuccess: Response<List<UserResponse>> = Response.success(dummyListUserResponse)
    val apiResponseEmpty: Response<List<UserResponse>> = Response.success(emptyListUserResponse)
    val apiResponseError: Response<List<UserResponse>> = Response.error(400, "contentType".toResponseBody("application/json".toMediaTypeOrNull()))

    val dummyException = Exception("Dummy Exception")
}
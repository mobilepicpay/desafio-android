package com.picpay.desafio.android.data

import com.google.gson.Gson
import org.junit.Test

class UserResponseTest {

    @Test
    fun createUserResponse_Test() {
        val json = "{\n" +
                "  \"id\": \"1\",\n" +
                "  \"name\": \"Pic Pay\",\n" +
                "  \"username\": \"picpay\",\n" +
                "  \"img\": \"picPay.jpg\"\n" +
                "}"
        val userResponse = Gson().fromJson(json, UserResponse::class.java)
        assert(userResponse != null)
        assert(userResponse.id == 1)
        assert(userResponse.name == "Pic Pay")
        assert(userResponse.username == "picpay")
        assert(userResponse.img == "picPay.jpg")
    }
}

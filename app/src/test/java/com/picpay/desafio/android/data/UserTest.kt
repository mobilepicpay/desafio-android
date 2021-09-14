package com.picpay.desafio.android.data

import org.junit.Test

class UserTest {

    @Test
    fun createUser_Test() {
        val user = User(name ="Pic Pay", username = "picpay", img = "picPay.jpg")

        assert(user.component1() == user.name)
        assert(user.component2() == user.username)
        assert(user.component3() == user.img)
    }
}

package com.picpay.desafio.android.data

import org.junit.Test

class UserTest {

    @Test
    fun createUser_Test() {
        val user = User(id = 1, name ="Pic Pay", username = "picpay", img = "picPay.jpg")

        assert(user.component1() == user.id)
        assert(user.component2() == user.name)
        assert(user.component3() == user.username)
        assert(user.component4() == user.img)
    }
}

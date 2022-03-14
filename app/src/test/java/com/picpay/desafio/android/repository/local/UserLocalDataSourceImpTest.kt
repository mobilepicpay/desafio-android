package com.picpay.desafio.android.repository.local

import com.picpay.desafio.android.database.dao.UserDao
import com.picpay.desafio.android.repository.model.User
import com.picpay.desafio.android.repository.model.UserLocal
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class UserLocalDataSourceImpTest {

    private val daoMock: UserDao = mockk()
    private val userLocalDataSourceImp: UserLocalDataSourceImp = UserLocalDataSourceImp(daoMock)

    @Test
    fun must_devolver_list_user_local_when_receive_list_user() {
        val userList = arrayListOf(User(img = "", name = "Roberta", id = 1, username = "Maria"))
        val userListLocal = arrayListOf(UserLocal(img = "", name = "Roberta", id = 1, username = "Maria"))

        val listConverted = userLocalDataSourceImp.convertForUserLocalList(userList)

        Assert.assertEquals(userListLocal, listConverted)
    }
}
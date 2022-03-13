package com.picpay.desafio.android.repository.local


import com.picpay.desafio.android.database.dao.UserDao
import com.picpay.desafio.android.repository.model.User
import com.picpay.desafio.android.repository.model.UserLocal
import io.mockk.mockk
import org.hamcrest.Matchers.contains
import org.junit.Assert
import org.junit.Test


class UserLocalDataSourceImpTest {

    private val userList = arrayListOf<User>()
    private val userListLocal = arrayListOf<UserLocal>()
    private val daoMock: UserDao = mockk()

    private val userLocalDataSourceImp: UserLocalDataSourceImp = (UserLocalDataSourceImp(daoMock))

    @Test
    fun deve_devolver_lista_user_local_quando_recebe_lista_user() {
        //userList.add(User(img = "", name = "Roberta", id = 1, username = "Maria"))
        //userListLocal.add(UserLocal(img = "", name = "Roberta", id = 1, username = "Maria"))

        userList.add(User(img = "", name = "Roberta", id = 1, username = "Maria"))
        userListLocal.add(UserLocal(img = "", name = "Roberta", id = 1, username = "Maria"))

        //every { userList. } answers { listOf<User>(User(img = "", name = "Roberta", id = 1, username = "Maria"))}


        val listConverted = userLocalDataSourceImp.convertForUserLocalList(userList)

//        MatcherAssert.assertThat(listConverted, CoreMatchers.both(Matchers.hasSize<Any>(1)).and(
//            Matchers.contains(UserLocal(img = "", name = "Roberta", id = 1, username = "Maria"))))

        Assert.assertEquals(listConverted, contains
            (UserLocal(img = "", name = "Roberta", id = 1, username = "Maria")))
    }
}
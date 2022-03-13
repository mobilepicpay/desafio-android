package com.picpay.desafio.android.repository

import com.picpay.desafio.android.database.dao.UserDao
import com.picpay.desafio.android.repository.local.UserLocalDataSource
import com.picpay.desafio.android.repository.local.UserLocalDataSourceImp
import com.picpay.desafio.android.repository.model.User
import com.picpay.desafio.android.repository.remote.UserRemoteDataSource
import com.picpay.desafio.android.repository.remote.UserRemoteDataSourceImp
import com.picpay.desafio.android.repository.remote.webclient.PicPayService
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

class UserRepositoryImpTest {

    private var picPayServiceMock: PicPayService = mockk()
    private var userDaoMock: UserDao = mockk()

    private val userRemoteDataSource: UserRemoteDataSource = spyk(UserRemoteDataSourceImp(picPayServiceMock))
    private val userLocalDataSource: UserLocalDataSource = spyk(UserLocalDataSourceImp(userDaoMock))

    private val repository: UserRepository =  spyk(UserRepositoryImp(userRemoteDataSource, userLocalDataSource))

    @Test
    fun deve_inserir_lista_quando_api_retornar_sucesso() {
        val sucess: () -> Unit = {}
        val sucessSlot = slot<() -> Unit>()
        val failure: (String) -> Unit = {}

        every { userRemoteDataSource.getUser(success = any(), failure = any()) } answers {
            firstArg<(List<User>) -> Unit>().invoke(listOf())
        }
        coEvery { userLocalDataSource.insert(any()) } answers {}

        runBlocking { repository.getUsers(success = sucess, failure = failure) }

        coVerify { userLocalDataSource.insert(any()) }
        verify { repository.getUsers(success = capture(sucessSlot), failure = any()) }

    }

    @Test
    fun deve_notificar_falha_quando_api_retornar_falha() {
        val sucess: () -> Unit = {}
        val failure: (String) -> Unit = {}
        val failureSlot = slot<(String) -> Unit>()
        failureSlot.captured = failure

        every { userRemoteDataSource.getUser(any(), any()) } answers {
            secondArg<(String) -> Unit>().invoke("teste")
        }

        runBlocking { repository.getUsers(success = sucess, failure = failure) }

        verify { repository.getUsers(success = any(), failure = capture(failureSlot)) }

    }
}
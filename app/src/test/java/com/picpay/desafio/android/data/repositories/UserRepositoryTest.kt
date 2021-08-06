package com.picpay.desafio.android.data.repositories

import com.picpay.desafio.android.base.BaseTest
import com.picpay.desafio.android.data.datasources.UserDataSource
import com.picpay.desafio.android.data.datasources.UserDataSourceInterface
import com.picpay.desafio.android.data.network.reponses.ApiResponse
import com.picpay.desafio.android.data.utils.Resource
import com.picpay.desafio.android.models.User
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import retrofit2.Response

@ExperimentalCoroutinesApi
class UserRepositoryTest : BaseTest(){

    @Mock
    private lateinit var dataSource: UserDataSourceInterface
    private lateinit var repository: UserRepository


    //region USER
    @Test
    fun testUserListWhenIsRefreshSuccessShouldReturnFromRemote() = runBlocking {
        val list = listOf(
            User(
            "https://randomuser.me/api/portraits/men/9.jpg","Eduardo Santos", 1001,"@eduardo.santos"
        ), User(
            "https://randomuser.me/api/portraits/men/9.jpg","Eduardo Santos 2", 1002,"@eduardo.santos2"
        )
        )

        Mockito.`when`(dataSource.getFromRemote()).thenReturn(
            flowOf(
                ApiResponse.create(Response.success(list))
            )
        )

        Mockito.`when`(dataSource.getFromLocal()).thenReturn(
            flowOf(
                list
            )
        )

        repository = UserRepository(dataSource)

        val result = repository.getUsers(true).toList()

        assertEquals(Resource.Status.LOADING, result[0].status)
        assertEquals(Resource.Status.SUCCESS, result[1].status)
        assertEquals(list, result[1].data)
    }

    @Test
    fun testUserListWhenIsNotRefreshSuccessShouldReturnCache() = runBlocking {
        val list = listOf(
            User(
                "https://randomuser.me/api/portraits/men/9.jpg","Eduardo Santos", 1001,"@eduardo.santos"
            ), User(
                "https://randomuser.me/api/portraits/men/9.jpg","Eduardo Santos 2", 1002,"@eduardo.santos2"
            )
        )

        Mockito.`when`(dataSource.getFromLocal()).thenReturn(
            flowOf(
                list
            )
        )

        repository = UserRepository(dataSource)

        val result = repository.getUsers(false).toList()

        assertEquals(Resource.Status.LOADING, result[0].status)
        assertEquals(Resource.Status.SUCCESS, result[1].status)
        assertEquals(list, result[1].data)
    }

    @Test
    fun testUserListWhenIsErrorShouldReturnError() = runBlocking {
        Mockito.`when`(dataSource.getFromLocal()).thenReturn(
            flowOf(
                null
            )
        )
        Mockito.`when`(dataSource.getFromRemote()).thenReturn(
            flowOf(
                ApiResponse.create(Response.error(404, ResponseBody.create(null, "Error")))
            )
        )
        repository = UserRepository(dataSource)

        val result = repository.getUsers(true).toList()

        assertEquals(Resource.Status.LOADING, result[0].status)
        assertEquals(Resource.Status.ERROR, result[1].status)
    }

    @Test
    fun testUserListWhenIsFailureShouldReturnFailure() = runBlocking {
        Mockito.`when`(dataSource.getFromLocal()).thenReturn(
            flowOf(
                null
            )
        )
        Mockito.`when`(dataSource.getFromRemote()).thenReturn(
            flowOf(
                ApiResponse.create(Throwable())
            )
        )
        repository = UserRepository(dataSource)

        val result = repository.getUsers(false).toList()

        assertEquals(Resource.Status.LOADING, result[0].status)
        assertEquals(Resource.Status.ERROR, result[1].status)
    }
    //endregion
}
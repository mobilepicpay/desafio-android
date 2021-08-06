package com.picpay.desafio.android.data.datasources

import com.picpay.desafio.android.base.BaseTest
import com.picpay.desafio.android.data.network.api.services.PicPayService
import com.picpay.desafio.android.data.network.reponses.ApiErrorResponse
import com.picpay.desafio.android.data.network.reponses.ApiResponse
import com.picpay.desafio.android.data.network.reponses.ApiSuccessResponse
import com.picpay.desafio.android.models.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import retrofit2.Response

@ExperimentalCoroutinesApi
class UserDataSourceTest : BaseTest(){
    companion object {
        const val IS_ERROR = "Error"
        const val CODE = 500
    }
    @Mock
    private lateinit var service: PicPayService
    private lateinit var dataSource: UserDataSource

    //region USER
    @Test
    fun testUserListWhenIsSuccessShouldReturnList() = runBlocking {
        val list = listOf(
            User(
            "https://randomuser.me/api/portraits/men/9.jpg","Eduardo Santos", 1001,"@eduardo.santos"
        ), User(
            "https://randomuser.me/api/portraits/men/9.jpg","Eduardo Santos 2", 1002,"@eduardo.santos2"
        )
        )

        Mockito.`when`(service.getUsers()).thenReturn(
            flowOf(
                ApiResponse.create(
                    Response.success(
                        list
                    )
                )
            )
        )
        dataSource = UserDataSource(service)

        val result: ApiResponse<List<User>> = dataSource.getFromRemote().single()
        val body = (result as ApiSuccessResponse).body

        assertEquals(list, body)
    }

    @Test
    fun testUserListWhenIsErrorShouldReturnError() = runBlocking {

        Mockito.`when`(service.getUsers()).thenReturn(
            flowOf(
                ApiResponse.create(
                    Response.error(
                        CODE, ResponseBody.create(null,
                            IS_ERROR
                        ))
                )
            )
        )
        dataSource = UserDataSource(service)
        val result: ApiResponse<List<User>> = dataSource.getFromRemote().single()
        val error = (result as ApiErrorResponse)

        assertEquals(IS_ERROR, error.errorMessage)
        assertEquals(CODE,  error.statusCode)
    }

    @Test
    fun testUserListWhenIsFailureShouldReturnFailure() = runBlocking {
        Mockito.`when`(service.getUsers()).thenReturn(
            flowOf(
                ApiResponse.create(Throwable(IS_ERROR))
            )
        )
        dataSource = UserDataSource(service)
        val result: ApiResponse<List<User>> = dataSource.getFromRemote().single()
        val error = (result as ApiErrorResponse)

        assertEquals(IS_ERROR, error.errorMessage)
        assertEquals(CODE,  error.statusCode)
    }
   //endregion

}
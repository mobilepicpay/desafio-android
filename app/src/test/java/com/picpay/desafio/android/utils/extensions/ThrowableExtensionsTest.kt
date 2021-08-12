package com.picpay.desafio.android.utils.extensions

import android.database.sqlite.SQLiteDatabaseCorruptException
import com.picpay.desafio.android.utils.sealeds.DataErrorException
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response.error
import java.net.HttpURLConnection
import java.net.UnknownHostException

class ThrowableExtensionsTest {

    @Test
    fun `handleApiException, when throwable is an UnknownHostException, it returns the network exception`() {
        val throwable = UnknownHostException()

        val resultedException = throwable.handleApiException()
        assertThat(resultedException, `is`(DataErrorException.ApiErrorException.NetworkConnectionException))
    }

    @Test
    fun `handleApiException, when throwable is a HttpException and has HTTP_NOT_FOUND code, it returns the not found exception`() {
        val throwable = HttpException(error<ResponseBody>(HttpURLConnection.HTTP_NOT_FOUND, "response".toResponseBody()))

        val resultedException = throwable.handleApiException()
        assertThat(resultedException, `is`(DataErrorException.ApiErrorException.NotFoundException))
    }

    @Test
    fun `handleApiException, when throwable is a HttpException and has HTTP_FORBIDDEN code, it returns the forbidden exception`() {
        val throwable = HttpException(error<ResponseBody>(HttpURLConnection.HTTP_FORBIDDEN, "response".toResponseBody()))

        val resultedException = throwable.handleApiException()
        assertThat(resultedException, `is`(DataErrorException.ApiErrorException.ForbiddenException))
    }

    @Test
    fun `handleApiException, when throwable is a HttpException and has HTTP_UNAVAILABLE code, it returns the service unavailable exception`() {
        val throwable = HttpException(error<ResponseBody>(HttpURLConnection.HTTP_UNAVAILABLE, "response".toResponseBody()))

        val resultedException = throwable.handleApiException()
        assertThat(resultedException, `is`(DataErrorException.ApiErrorException.ServiceUnavailableException))
    }

    @Test
    fun `handleApiException, when throwable is a HttpException and has unknown code, it returns an unknown exception`() {
        val throwable = HttpException(error<ResponseBody>(HttpURLConnection.HTTP_BAD_METHOD, "response".toResponseBody()))

        val resultedException = throwable.handleApiException()
        assertThat(resultedException, `is`(DataErrorException.ApiErrorException.UnknownException))
    }

    @Test
    fun `handleApiException, when throwable isn't neither UnknownHostException a nor HttpException, it returns an unknown exception`() {
        val throwable = Throwable()

        val resultedException = throwable.handleApiException()
        assertThat(resultedException, `is`(DataErrorException.ApiErrorException.UnknownException))
    }

    @Test
    fun `handleDatabaseException, when throwable is a SQLiteException, it returns an sqlite exception`() {
        val throwable = SQLiteDatabaseCorruptException()

        val resultedException = throwable.handleDatabaseException()
        assertThat(resultedException, `is`(DataErrorException.DatabaseErrorException.SqliteException))
    }

    @Test
    fun `handleDatabaseException, when throwable isn't a SQLiteException, it returns an unknown exception`() {
        val throwable = Throwable()

        val resultedException = throwable.handleDatabaseException()
        assertThat(resultedException, `is`(DataErrorException.DatabaseErrorException.UnknownException))
    }
}

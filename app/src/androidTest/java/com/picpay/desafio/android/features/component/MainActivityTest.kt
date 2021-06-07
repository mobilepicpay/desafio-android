package com.picpay.desafio.android.features.component

import android.content.Intent
import com.picpay.desafio.android.features.BaseMainActivityTest
import com.picpay.desafio.android.features.main
import com.picpay.desafio.android.usecases.GetUsersUseCases
import io.mockk.coEvery
import kotlinx.coroutines.flow.flow
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainActivityTest : BaseMainActivityTest() {

    @Test
    fun whenFetchUsers_and_shouldShowUsersRemote() {
        //ARRANGE
        coEvery {
            usersUseCases.getUsers()
        } coAnswers {
            flow {
                emit(
                    GetUsersUseCases.ResultUsers.UsersList(USERS_MOCK_RESULT)
                )
            }
        }

        //ACT
        activityRule.launchActivity(Intent())

        //ASSERT
        main {
            clickAtPosition(0)
        }

        activityRule.finishActivity()
    }
}
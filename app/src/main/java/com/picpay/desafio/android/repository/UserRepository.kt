package com.picpay.desafio.android.repository

import android.util.Log
import androidx.lifecycle.asLiveData
import com.picpay.desafio.android.database.UserDao
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.service.UserRestApiTask
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import retrofit2.awaitResponse
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userRestApiTask: UserRestApiTask,
    private val executor: Executor,
    private val userDao: UserDao
) {
//    companion object {
//        const val TAG = "UserRepository"
//    }

//    private val userList = arrayListOf<User>()

    fun getUsers(): Flow<List<User>> {
        refreshUsers()
        return userDao.load()
    }

    private fun refreshUsers() {
        val users = userDao.load()
        if (users.asLiveData().value == null) {
            val response = userRestApiTask.retrofitApi().getUsers().execute()

            userDao.save(response.body()!!)
        }
    }

    companion object {
        val FRESH_TIMEOUT = TimeUnit.DAYS.toMillis(1)
    }


//    fun getUsers(): List<User> {
//
//        val request = userRestApiTask.retrofitApi().getUsers().execute()
//
//        if (request.isSuccessful) {
//            request.body()?.let {
//                userList.addAll(it)
//            }
//        } else {
//            request.errorBody()?.let {
//                Log.d(TAG, it.toString())
//            }
//        }
//
//        return userList
//    }
}
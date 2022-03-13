package com.picpay.desafio.android.repository.local

import androidx.lifecycle.LiveData
import com.picpay.desafio.android.database.dao.UserDao
import com.picpay.desafio.android.repository.model.User
import com.picpay.desafio.android.repository.model.UserLocal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserLocalDataSourceImp(private val dao: UserDao) : UserLocalDataSource {

    override fun insert(users: List<User>) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(convertForUserLocalList(users))
        }
    }

    override fun getUser(): LiveData<List<UserLocal>?> {
        return dao.getUser()
    }

     fun convertForUserLocalList(users: List<User>): List<UserLocal> {
        return users.map { user ->
            UserLocal(
                img = user.img,
                name = user.name,
                id = user.id,
                username = user.username
            )
        }
    }
}
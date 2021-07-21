package com.picpay.desafio.android.repository

import androidx.lifecycle.liveData
import com.picpay.desafio.android.database.dao.UserEntityDao
import com.picpay.desafio.android.model.network.User
import com.picpay.desafio.android.retrofit.webclient.PicPayWebClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class UserRepository(
    private val userDao: UserEntityDao,
    private val picpayWebClient : PicPayWebClient
) {

   suspend fun getAllUsers() : Resource<List<User>?>{
       return withContext(Dispatchers.IO){
           //Tenta resgatar dados do cache
           val cacheUsrs = getUserFromCache()
           if(!cacheUsrs.isNullOrEmpty()){
               Resource.Success(data = cacheUsrs)
           }else{
               val apiUsers = getUserFromApi()
               if(apiUsers is Resource.Success && !apiUsers.data.isNullOrEmpty()) {
                   saveApiUsrInCache(apiUsers.data)
                   apiUsers
               }else{
                   apiUsers
               }
           }
       }
   }

    private suspend fun saveApiUsrInCache(apiUsers: List<User>?) {
        withContext(Dispatchers.IO){
            userDao.insertUserList(
                apiUsers?.map {
                    it.convertToUserDomain()
                } ?: emptyList()
            )
        }
    }

    suspend fun getUserFromApi() : Resource<List<User>?> {
        return withContext(Dispatchers.IO) {
            val response = picpayWebClient.getAllUsers()
            if (response.isSuccessful) {
                Resource.Success(data = response.body())
            } else {
                Resource.Error(Exception("Erro ao buscar lista de usuarios"))
            }
        }
    }

    suspend fun getUserFromCache() : List<User> {
        return withContext(Dispatchers.IO){
            val dbUsers = userDao.getUsers()
            //
            val networkUsers = dbUsers.map {
                it.convertToUserNetwork()
            }
            //
            if(!networkUsers.isNullOrEmpty()){
                networkUsers
            }else{
                emptyList<User>()
            }
        }
    }

}
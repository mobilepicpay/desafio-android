package com.picpay.desafio.android.ui.presentation

import com.picpay.desafio.android.data.User

class UserMapper {

    fun responseToViewObject(response: List<User>): ArrayList<UserViewObject> {
        val userList : ArrayList<UserViewObject> = ArrayList()

        response.forEach{ user ->
            userList.add(
                UserViewObject(
                    name = user.name ?: "",
                    user.username?: "",
                    user.img?: ""
                )
            )
        }
        return userList
    }
}
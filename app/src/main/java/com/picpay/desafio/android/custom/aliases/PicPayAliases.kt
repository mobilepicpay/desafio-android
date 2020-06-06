package com.picpay.desafio.android.custom.aliases

import com.picpay.desafio.android.room.models.User
import com.picpay.desafio.android.base.Result

typealias UsersResult = Result<ListOfUsers>

typealias ListOfUsers = List<User>

typealias UserResult = Result<User>

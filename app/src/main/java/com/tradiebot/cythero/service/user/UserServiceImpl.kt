package com.tradiebot.cythero.service.user

import com.tradiebot.cythero.domain.user.service.UserService
import com.tradiebot.cythero.domain.user.model.UserComplete
import com.tradiebot.cythero.domain.user.model.UserLoginUpdate
import logcat.logcat

class UserServiceImpl : UserService{

    override suspend fun loginUser(user: UserLoginUpdate): UserComplete {
        logcat { "meow" }
        TODO("Not yet implemented")
    }

}
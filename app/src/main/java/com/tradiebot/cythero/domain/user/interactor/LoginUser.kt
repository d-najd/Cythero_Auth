package com.tradiebot.cythero.domain.user.interactor

import com.tradiebot.cythero.domain.user.model.UserComplete
import com.tradiebot.cythero.domain.user.model.UserLoginUpdate
import com.tradiebot.cythero.service.user.UserServiceImpl

class LoginUser(
    private val userService: UserServiceImpl,
) {

    suspend fun await(user: UserLoginUpdate): UserComplete {
        return userService.loginUser(user);
    }

}
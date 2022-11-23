package com.tradiebot.cythero.domain.user.service

import com.tradiebot.cythero.domain.user.model.UserLoginApi

interface UserService {

    suspend fun loginUser(user: UserLoginApi)


}
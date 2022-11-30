package com.tradiebot.cythero.domain.auth.service

import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.domain.user.model.UserLogin
import com.tradiebot.cythero.domain.user.model.UserRegister

interface AuthService {

    suspend fun loginUser(user: UserLogin): Auth?

    suspend fun registerUser(user: UserRegister): Auth?

}
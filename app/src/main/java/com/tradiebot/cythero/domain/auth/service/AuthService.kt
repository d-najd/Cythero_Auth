package com.tradiebot.cythero.domain.auth.service

import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.domain.user.model.UserLogin
import com.tradiebot.cythero.domain.user.model.UserRegister
import java.util.*

interface AuthService {

    suspend fun loginUser(user: UserLogin): Optional<Auth>

    suspend fun registerUser(user: UserRegister): Optional<Auth>

}
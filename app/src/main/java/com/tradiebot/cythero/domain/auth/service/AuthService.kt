package com.tradiebot.cythero.domain.auth.service

import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.domain.user.model.User
import com.tradiebot.cythero.domain.user.model.UserLogin
import java.util.*

interface AuthService {

    /**
     * @return null in case the user credentials are wrong
     */
    suspend fun loginUser(user: UserLogin): Optional<Auth>

}
package com.tradiebot.cythero.network.auth

import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.domain.auth.service.AuthService
import com.tradiebot.cythero.domain.user.model.UserLogin
import com.tradiebot.cythero.domain.user.model.UserRegister

@Suppress("RedundantNullableReturnType")
object AuthServiceMock : AuthService {

    override suspend fun loginUser(user: UserLogin): Auth? {
        return Auth.testingInstance()
    }

    override suspend fun registerUser(user: UserRegister): Auth? {
        return Auth.testingInstance()
    }
}

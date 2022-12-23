package com.tradiebot.cythero.domain.auth.interactor

import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.domain.auth.service.AuthService
import com.tradiebot.cythero.domain.user.model.UserLogin

class LoginUser(
    private val authService: AuthService,
) {
    suspend fun await(user: UserLogin): Auth? {
        return authService.loginUser(user)
    }
}
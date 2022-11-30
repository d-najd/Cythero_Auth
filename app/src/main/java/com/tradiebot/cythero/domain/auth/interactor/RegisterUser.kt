package com.tradiebot.cythero.domain.auth.interactor

import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.domain.auth.service.AuthService
import com.tradiebot.cythero.domain.user.model.UserRegister

class RegisterUser(
    private val authService: AuthService,
) {
    suspend fun await(user: UserRegister): Auth? {
        return authService.registerUser(user)
    }
}
package com.tradiebot.cythero.domain.user.service

import com.tradiebot.cythero.domain.user.model.UserComplete
import com.tradiebot.cythero.domain.user.model.UserLoginUpdate

interface UserService {

    suspend fun loginUser(user: UserLoginUpdate): UserComplete?

}
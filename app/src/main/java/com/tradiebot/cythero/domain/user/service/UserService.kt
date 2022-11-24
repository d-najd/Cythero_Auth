package com.tradiebot.cythero.domain.user.service

import com.tradiebot.cythero.domain.user.model.UserComplete
import com.tradiebot.cythero.domain.user.model.UserLoginUpdate

interface UserService {

    /**
     * @return null in case the user credentials are wrong
     */
    suspend fun loginUser(user: UserLoginUpdate): UserComplete?

}
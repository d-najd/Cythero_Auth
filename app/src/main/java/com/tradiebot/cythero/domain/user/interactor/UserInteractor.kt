package com.tradiebot.cythero.domain.user.interactor

import com.tradiebot.cythero.domain.user.model.UserComplete
import com.tradiebot.cythero.domain.user.model.UserLoginApi

interface UserInteractor {

    suspend fun loginUser(user: UserLoginApi): UserComplete

}
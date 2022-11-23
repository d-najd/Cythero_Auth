package com.tradiebot.cythero.interactor.user

import com.tradiebot.cythero.domain.user.interactor.UserInteractor
import com.tradiebot.cythero.domain.user.model.UserComplete
import com.tradiebot.cythero.domain.user.model.UserLoginApi
import logcat.logcat

class UserInteractorImpl : UserInteractor{

    override suspend fun loginUser(user: UserLoginApi): UserComplete {
        logcat { "meow" }
        TODO("Not yet implemented")
    }

}
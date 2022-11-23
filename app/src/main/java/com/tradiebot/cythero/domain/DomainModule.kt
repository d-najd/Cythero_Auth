package com.tradiebot.cythero.domain

import com.tradiebot.cythero.domain.user.interactor.LoginUser
import com.tradiebot.cythero.domain.user.service.UserService
import com.tradiebot.cythero.network.user.UserServiceImpl
import uy.kohesive.injekt.api.*

class DomainModule : InjektModule {

    override fun InjektRegistrar.registerInjectables() {
        addSingletonFactory<UserService> { UserServiceImpl() }
        addFactory { LoginUser(get()) }
    }

}

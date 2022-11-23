package com.tradiebot.cythero.domain

import com.tradiebot.cythero.domain.user.interactor.LoginUser
import com.tradiebot.cythero.domain.user.service.UserService
import com.tradiebot.cythero.service.user.UserServiceImpl
import uy.kohesive.injekt.api.InjektModule
import uy.kohesive.injekt.api.InjektRegistrar
import uy.kohesive.injekt.api.addFactory
import uy.kohesive.injekt.api.addSingletonFactory
import uy.kohesive.injekt.api.get

class DomainModule : InjektModule {

    override fun InjektRegistrar.registerInjectables() {
        addSingletonFactory<UserService> { UserServiceImpl() }
        addFactory { LoginUser(get()) }
    }

}

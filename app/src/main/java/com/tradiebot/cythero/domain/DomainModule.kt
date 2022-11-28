package com.tradiebot.cythero.domain

import com.tradiebot.cythero.domain.auth.interactor.LoginUser
import com.tradiebot.cythero.domain.auth.interactor.RegisterUser
import com.tradiebot.cythero.domain.auth.service.AuthService
import com.tradiebot.cythero.network.auth.AuthServiceImpl
import uy.kohesive.injekt.api.*

class DomainModule : InjektModule {

    override fun InjektRegistrar.registerInjectables() {
        addSingletonFactory<AuthService> { AuthServiceImpl }
        addFactory { LoginUser(get()) }
        addFactory { RegisterUser(get()) }
    }

}

package com.tradiebot.cythero.domain

import com.tradiebot.cythero.domain.analytics.user.interactor.RequestUserAnalytics
import com.tradiebot.cythero.domain.analytics.user.service.AnalyticsUserService
import com.tradiebot.cythero.domain.auth.interactor.LoginUser
import com.tradiebot.cythero.domain.auth.interactor.RegisterUser
import com.tradiebot.cythero.domain.auth.service.AuthService
import com.tradiebot.cythero.network.analytics.user.AnalyticsUserServiceMock
import com.tradiebot.cythero.network.auth.AuthServiceMock
import uy.kohesive.injekt.api.*

class DomainModule : InjektModule {

    override fun InjektRegistrar.registerInjectables() {
        addSingletonFactory<AuthService> { AuthServiceMock }
        // addSingletonFactory<AuthService> { AuthServiceImpl }
        addFactory { LoginUser(get()) }
        addFactory { RegisterUser(get()) }

        addSingletonFactory<AnalyticsUserService> { AnalyticsUserServiceMock }
        // addSingletonFactory<AnalyticsService> { AnalyticsServiceImpl }
        addFactory { RequestUserAnalytics(get()) }
    }

}

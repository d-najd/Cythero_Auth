package com.tradiebot.cythero.domain

import com.google.gson.GsonBuilder
import com.tradiebot.cythero.domain.analytics.user.interactor.RequestUserAnalytics
import com.tradiebot.cythero.domain.analytics.user.service.AnalyticsUserService
import com.tradiebot.cythero.domain.auth.interactor.LoginUser
import com.tradiebot.cythero.domain.auth.interactor.RegisterUser
import com.tradiebot.cythero.domain.auth.service.AuthService
import com.tradiebot.cythero.network.analytics.user.AnalyticsUserServiceImpl
import com.tradiebot.cythero.network.analytics.user.AnalyticsUserServiceMock
import com.tradiebot.cythero.network.auth.AuthServiceMock
import com.tradiebot.cythero.util.CytheroDateFormat
import okhttp3.OkHttpClient
import uy.kohesive.injekt.api.*

class DomainModule : InjektModule {

    override fun InjektRegistrar.registerInjectables() {
        addSingletonFactory {
            OkHttpClient()
        }

        addSingletonFactory {
            GsonBuilder()
                .setDateFormat(CytheroDateFormat.defaultRequestDateFormat().toPattern())
                .create()
        }

        addSingletonFactory<AuthService> { AuthServiceMock }
        // addSingletonFactory<AuthService> { AuthServiceImpl }
        addFactory { LoginUser(get()) }
        addFactory { RegisterUser(get()) }

        // addSingletonFactory<AnalyticsUserService> { AnalyticsUserServiceMock }
        addSingletonFactory<AnalyticsUserService> { AnalyticsUserServiceImpl }
        addFactory { RequestUserAnalytics(get()) }
    }
}

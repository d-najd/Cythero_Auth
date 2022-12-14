package com.tradiebot.cythero.domain

import com.google.gson.GsonBuilder
import com.tradiebot.cythero.domain.analytics.part.interactor.RequestPartAnalytics
import com.tradiebot.cythero.domain.analytics.part.service.AnalyticsPartService
import com.tradiebot.cythero.domain.analytics.usage.interactor.RequestUsageAnalytics
import com.tradiebot.cythero.domain.analytics.usage.service.AnalyticsUsageService
import com.tradiebot.cythero.domain.analytics.user.interactor.RequestUserAnalytics
import com.tradiebot.cythero.domain.analytics.user.service.AnalyticsUserService
import com.tradiebot.cythero.domain.auth.interactor.LoginUser
import com.tradiebot.cythero.domain.auth.interactor.RegisterUser
import com.tradiebot.cythero.domain.auth.service.AuthService
import com.tradiebot.cythero.network.analytics.part.AnalyticsPartServiceImpl
import com.tradiebot.cythero.network.analytics.part.AnalyticsPartServiceMock
import com.tradiebot.cythero.network.analytics.usage.AnalyticsUsageServiceImpl
import com.tradiebot.cythero.network.analytics.usage.AnalyticsUsageServiceMock
import com.tradiebot.cythero.network.analytics.user.AnalyticsUserServiceImpl
import com.tradiebot.cythero.network.analytics.user.AnalyticsUserServiceMock
import com.tradiebot.cythero.network.auth.AuthServiceImpl
import com.tradiebot.cythero.network.auth.AuthServiceMock
import com.tradiebot.cythero.util.CytheroDateFormat
import okhttp3.OkHttpClient
import uy.kohesive.injekt.api.*

class DomainModule : InjektModule {
    private val USE_MOCKS = true
    
    override fun InjektRegistrar.registerInjectables() {
        addSingletonFactory {
            OkHttpClient()
        }
        
        addSingletonFactory {
            GsonBuilder()
                .setDateFormat(CytheroDateFormat.defaultRequestDateFormat().toPattern())
                .create()
        }
        
        when (USE_MOCKS) {
            true -> {
                addSingletonFactory<AuthService> { AuthServiceMock }
                addSingletonFactory<AnalyticsUserService> { AnalyticsUserServiceMock }
                addSingletonFactory<AnalyticsPartService> { AnalyticsPartServiceMock }
                addSingletonFactory<AnalyticsUsageService> { AnalyticsUsageServiceImpl }
            }
            false -> {
                addSingletonFactory<AuthService> { AuthServiceImpl }
                addSingletonFactory<AnalyticsUserService> { AnalyticsUserServiceImpl }
                addSingletonFactory<AnalyticsPartService> { AnalyticsPartServiceImpl }
                addSingletonFactory<AnalyticsUsageService> { AnalyticsUsageServiceImpl }
            }
        }
        
        addFactory { LoginUser(get()) }
        addFactory { RegisterUser(get()) }
        
        addFactory { RequestUserAnalytics(get()) }
        addFactory { RequestPartAnalytics(get()) }
        addFactory { RequestUsageAnalytics(get()) }
    }
}

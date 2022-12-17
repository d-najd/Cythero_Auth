package com.tradiebot.cythero.domain.analytics.usage.interactor

import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUpdate
import com.tradiebot.cythero.domain.analytics.usage.service.AnalyticsUsageService
import com.tradiebot.cythero.domain.auth.model.Auth
import java.util.*

class RequestUsageAnalytics(
    private val analyticsUsageService: AnalyticsUsageService,
) {

    @Suppress("unused")
    suspend fun await(userAuth: Auth, userIDs: List<Long>, dateRange: Pair<Date, Date>): AnalyticsUpdate? {
        return analyticsUsageService.getAnalytics(userAuth, userIDs, dateRange)
    }
    
    suspend fun await(userAuth: Auth, userID: Long, dateRange: Pair<Date, Date>): AnalyticsUpdate? {
        return analyticsUsageService.getAnalytics(userAuth, listOf(userID), dateRange)
    }

}
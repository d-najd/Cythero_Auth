package com.tradiebot.cythero.domain.analytics.usage.interactor

import com.tradiebot.cythero.domain.analytics.Part
import com.tradiebot.cythero.domain.analytics.part.model.AnalyticsPart
import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUsage
import com.tradiebot.cythero.domain.analytics.usage.service.AnalyticsUsageService
import com.tradiebot.cythero.domain.auth.model.Auth
import java.util.*

class RequestUsageAnalytics(
    private val analyticsUsageService: AnalyticsUsageService,
) {

    suspend fun await(userAuth: Auth, userIDs: List<Long>, dateRange: Pair<Date, Date>): AnalyticsUsage? {
        return analyticsUsageService.getAnalytics(userAuth, userIDs, dateRange)
    }

}
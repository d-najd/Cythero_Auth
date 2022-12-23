package com.tradiebot.cythero.network.analytics.usage

import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUsage
import com.tradiebot.cythero.domain.analytics.usage.service.AnalyticsUsageService
import com.tradiebot.cythero.domain.auth.model.Auth
import java.util.*

@Suppress("RedundantNullableReturnType")
object AnalyticsUsageServiceMock: AnalyticsUsageService {

    override suspend fun getAnalytics(
        userAuth: Auth,
        userIDs: List<Long>,
        dateRange: Pair<Date, Date>
    ): AnalyticsUsage? {
        return AnalyticsUsage.mockInstance()
    }
    
}

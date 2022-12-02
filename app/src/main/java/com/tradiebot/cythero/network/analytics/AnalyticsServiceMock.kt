package com.tradiebot.cythero.network.analytics

import com.tradiebot.cythero.domain.analytics.model.Analytics
import com.tradiebot.cythero.domain.analytics.service.AnalyticsService
import com.tradiebot.cythero.domain.auth.model.Auth

object AnalyticsServiceMock: AnalyticsService {
    override suspend fun getUserAnalytics(
        userAuth: Auth,
        userIDs: List<Long>,
    ): Map<Long, Analytics> {
        return Analytics.testingInstance()
    }
}

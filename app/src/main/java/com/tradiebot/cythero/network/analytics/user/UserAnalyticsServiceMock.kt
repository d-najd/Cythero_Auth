package com.tradiebot.cythero.network.analytics.user

import com.tradiebot.cythero.domain.analytics.user.model.Analytics
import com.tradiebot.cythero.domain.analytics.user.service.UserAnalyticsService
import com.tradiebot.cythero.domain.auth.model.Auth

object UserAnalyticsServiceMock: UserAnalyticsService {
    override suspend fun getUserAnalytics(
        userAuth: Auth,
        userIDs: List<Long>,
    ): Map<Long, Analytics> {
        return Analytics.testingInstance()
    }
}

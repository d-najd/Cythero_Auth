package com.tradiebot.cythero.network.analytics.user

import com.tradiebot.cythero.domain.analytics.user.model.AnalyticsUser
import com.tradiebot.cythero.domain.analytics.user.service.AnalyticsUserService
import com.tradiebot.cythero.domain.auth.model.Auth

object AnalyticsUserServiceMock: AnalyticsUserService {
    override suspend fun getAnalytics(
        userAuth: Auth,
        userIDs: List<Long>,
    ): Map<Long, AnalyticsUser> {
        return AnalyticsUser.mockInstance()
    }
}

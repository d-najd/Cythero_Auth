package com.tradiebot.cythero.network.analytics.user

import com.tradiebot.cythero.domain.analytics.user.model.AnalyticsUser
import com.tradiebot.cythero.domain.analytics.user.service.AnalyticsUserService
import com.tradiebot.cythero.domain.auth.model.Auth
import java.util.Date

object AnalyticsUserServiceMock: AnalyticsUserService {
    override suspend fun getAnalytics(
        userAuth: Auth,
        userIDs: List<Long>,
        dateRange: Pair<Date, Date>
    ): Map<Long, AnalyticsUser> {
        return AnalyticsUser.mockInstance()
    }
}

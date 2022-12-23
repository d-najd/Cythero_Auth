package com.tradiebot.cythero.domain.analytics.user.interactor

import com.tradiebot.cythero.domain.analytics.user.model.AnalyticsUser
import com.tradiebot.cythero.domain.analytics.user.service.AnalyticsUserService
import com.tradiebot.cythero.domain.auth.model.Auth
import java.util.*

class RequestUserAnalytics(
    private val analyticsUserService: AnalyticsUserService,
) {

    suspend fun await(userAuth: Auth, userIDs: List<Long>, dateRange: Pair<Date, Date>): Map<Long, AnalyticsUser> {
        return analyticsUserService.getAnalytics(userAuth, userIDs, dateRange)
    }

    suspend fun await(userAuth: Auth, userID: Long, dateRange: Pair<Date, Date>): AnalyticsUser? {
        return analyticsUserService.getAnalytics(userAuth, listOf(userID), dateRange)[userID]
    }

}

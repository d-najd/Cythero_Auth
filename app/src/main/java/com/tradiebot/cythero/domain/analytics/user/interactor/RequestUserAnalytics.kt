package com.tradiebot.cythero.domain.analytics.user.interactor

import com.tradiebot.cythero.domain.analytics.user.model.AnalyticsUser
import com.tradiebot.cythero.domain.analytics.user.service.AnalyticsUserService
import com.tradiebot.cythero.domain.auth.model.Auth
import java.util.*

class RequestUserAnalytics(
    private val analyticsUserService: AnalyticsUserService,
) {

    @Suppress("unused")
    suspend fun await(userAuth: Auth, userIDs: List<Long>): Map<Long, AnalyticsUser> {
        return analyticsUserService.getAnalytics(userAuth, userIDs)
    }

    suspend fun await(userAuth: Auth, userID: Long): AnalyticsUser? {
        return analyticsUserService.getAnalytics(userAuth, listOf(userID))[userID]
    }

}

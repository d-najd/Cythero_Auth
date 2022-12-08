package com.tradiebot.cythero.domain.analytics.user.interactor

import com.tradiebot.cythero.domain.analytics.user.model.UserAnalytics
import com.tradiebot.cythero.domain.analytics.user.service.UserAnalyticsService
import com.tradiebot.cythero.domain.auth.model.Auth
import java.util.*

class RequestUserAnalytics(
    private val userAnalyticsService: UserAnalyticsService,
) {

    @Suppress("unused")
    suspend fun await(userAuth: Auth, userIDs: List<Long>): Map<Long, UserAnalytics> {
        return userAnalyticsService.getUserAnalytics(userAuth, userIDs)
    }

    suspend fun await(userAuth: Auth, userID: Long): UserAnalytics? {
        return userAnalyticsService.getUserAnalytics(userAuth, listOf(userID))[userID]
    }

}

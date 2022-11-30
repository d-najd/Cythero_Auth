package com.tradiebot.cythero.domain.analytics.interactor

import com.tradiebot.cythero.domain.analytics.model.Analytics
import com.tradiebot.cythero.domain.analytics.service.AnalyticsService
import com.tradiebot.cythero.domain.auth.model.Auth
import java.util.*

class RequestAnalytics(
    private val analyticsService: AnalyticsService,
) {
    
    @Suppress("unused")
    suspend fun await(userAuth: Auth, userIDs: List<Long>): Map<Long, Analytics> {
        return analyticsService.getUserAnalytics(userAuth, userIDs)
    }

    suspend fun await(userAuth: Auth, userID: Long): Optional<Analytics> {
        return Optional.ofNullable(analyticsService.getUserAnalytics(userAuth, listOf(userID)).getOrDefault(userID, null))
    }

}

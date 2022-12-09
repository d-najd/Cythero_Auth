package com.tradiebot.cythero.domain.analytics.part.interactor

import com.tradiebot.cythero.domain.analytics.PartEnum
import com.tradiebot.cythero.domain.analytics.part.model.AnalyticsPart
import com.tradiebot.cythero.domain.analytics.part.service.AnalyticsPartService
import com.tradiebot.cythero.domain.auth.model.Auth

class RequestPartAnalytics(
    private val analyticsPartService: AnalyticsPartService,
) {

    suspend fun await(userAuth: Auth, userIDs: List<Long>, parts: List<PartEnum>): List<AnalyticsPart>{
        return analyticsPartService.getAnalytics(userAuth, userIDs, parts)
    }

    suspend fun await(userAuth: Auth, userID: Long, part: PartEnum): AnalyticsPart? {
        return analyticsPartService.getAnalytics(userAuth, listOf(userID), listOf(part)).getOrNull(0).takeIf { a -> a?.part == part }
    }

}

package com.tradiebot.cythero.network.analytics.part

import com.tradiebot.cythero.domain.analytics.Part
import com.tradiebot.cythero.domain.analytics.part.model.AnalyticsPart
import com.tradiebot.cythero.domain.analytics.part.model.AnalyticsParts
import com.tradiebot.cythero.domain.analytics.part.service.AnalyticsPartService
import com.tradiebot.cythero.domain.auth.model.Auth

object AnalyticsPartServiceMock: AnalyticsPartService {

    override suspend fun getAnalytics(
        userAuth: Auth,
        userIDs: List<Long>,
        parts: List<Part>
    ): List<AnalyticsPart> {
        return AnalyticsParts.mockInstance().AnalyticsParts
    }

}

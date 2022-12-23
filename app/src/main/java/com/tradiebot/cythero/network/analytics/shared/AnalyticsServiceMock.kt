package com.tradiebot.cythero.network.analytics.shared

import com.tradiebot.cythero.domain.analytics.shared.model.AnalyticSession
import com.tradiebot.cythero.domain.analytics.shared.model.AnalyticsLabel
import com.tradiebot.cythero.domain.analytics.shared.service.AnalyticsService
import com.tradiebot.cythero.domain.auth.model.Auth
import java.util.*

@Suppress("RedundantNullableReturnType")
object AnalyticsServiceMock: AnalyticsService {

    override suspend fun getLabels(
        userAuth: Auth
    ): List<AnalyticsLabel> {
        return AnalyticsLabel.mockInstance()
    }
    
    override suspend fun getSessionInfo(
        userAuth: Auth,
        sessionID: String
    ): List<AnalyticSession> {
        return AnalyticSession.mockInstance()
    }
    
}

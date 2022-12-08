package com.tradiebot.cythero.domain.analytics.part.service

import com.tradiebot.cythero.domain.analytics.PartsEnum
import com.tradiebot.cythero.domain.analytics.part.model.AnalyticsPart
import com.tradiebot.cythero.domain.auth.model.Auth

interface AnalyticsPartService {
    
    suspend fun getAnalytics(userAuth: Auth, userIDs: List<Long>, part: PartsEnum): List<AnalyticsPart>

}

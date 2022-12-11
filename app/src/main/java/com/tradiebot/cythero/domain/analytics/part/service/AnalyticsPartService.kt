package com.tradiebot.cythero.domain.analytics.part.service

import com.tradiebot.cythero.domain.analytics.Part
import com.tradiebot.cythero.domain.analytics.part.model.AnalyticsPart
import com.tradiebot.cythero.domain.auth.model.Auth

interface AnalyticsPartService {

    /**
     * return list of part analytics for the given user ids
     *
     * @param userAuth user which has privileges to request analytics for the given userIDs
     * @param userIDs ids of users which the analytics are requested for
     * @param parts parts for which the analytics are being requested
     * @return list of [AnalyticsPart] if something has gone wrong emptry list will be returned instead
     */
    suspend fun getAnalytics(userAuth: Auth, userIDs: List<Long>, parts: List<Part>): List<AnalyticsPart>

}

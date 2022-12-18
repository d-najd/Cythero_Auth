package com.tradiebot.cythero.domain.analytics.shared.service

import com.tradiebot.cythero.domain.analytics.shared.model.AnalyticsLabel
import com.tradiebot.cythero.domain.auth.model.Auth


interface AnalyticsService {

    /**
     * request analytics usage labels
     *
     * @param userAuth user which has privileges to request the labels
     * @return list of analytics labels, the list may contain null elements
     */
    suspend fun getLabels(userAuth: Auth): List<AnalyticsLabel>
    
}

package com.tradiebot.cythero.domain.analytics.usage.service

import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUsage
import com.tradiebot.cythero.domain.auth.model.Auth
import java.util.*


interface AnalyticsUsageService {

    /**
     * return analytics for the selected user/s
     *
     * @param userAuth user which has privileges to request analytics for the given userIDs
     * @param userIDs ids of users which the analytics are requested for
     * @param dateRange date range where the first is the start date and the second is the end date,
     * any analytics outside of this range will not be returned, the date range needs to be in
     * [com.tradiebot.cythero.util.CytheroDateFormat.DEFAULT_API_DATE_FORMAT] format
     * @return [AnalyticsUsage] for the given user/s, if something has gone wrong null will be returned instead
     */
    suspend fun getAnalytics(userAuth: Auth, userIDs: List<Long>, dateRange: Pair<Date, Date>): AnalyticsUsage?

}

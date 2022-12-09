package com.tradiebot.cythero.domain.analytics.user.service

import com.tradiebot.cythero.domain.analytics.user.model.AnalyticsUser
import com.tradiebot.cythero.domain.auth.model.Auth
import java.util.Date

interface AnalyticsUserService {

    /**
     * returns map of user analytics for the given userIDs and date range
     *
     * @param userAuth user which has privileges to request analytics for the given userIDs
     * @param userIDs ids of users which the analytics are requested for
     * @param dateRange date range where the first is the start date and the second is the end date,
     * any analytics outside of this range will not be returned, the date range needs to be in
     * [com.tradiebot.cythero.util.CytheroDateFormat.DEFAULT_API_DATE_FORMAT] format
     * @return map of analytics where the key is the user id and the value is the analytics mapped for
     * that user, if there are no analytics or IOException had happened empty map will be returned instead
     */
    suspend fun getAnalytics(userAuth: Auth, userIDs: List<Long>, dateRange: Pair<Date, Date>): Map<Long, AnalyticsUser>

}
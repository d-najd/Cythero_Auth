package com.tradiebot.cythero.domain.analytics.user.service

import com.tradiebot.cythero.domain.analytics.user.model.Analytics
import com.tradiebot.cythero.domain.auth.model.Auth

interface UserAnalyticsService {

    /**
     * returns map of user analytics the given userIDS,
     * <b>The user needs to have privileges to request UserAnalytics</b>
     *
     * For example an administrator will be able to see the analytics of multiple users so he will
     * be able to request multiple id's while a normal user will probably obly be able to request
     * analytics for himself
     *
     * @param userAuth userAuth which has privileges to request analytics for the given userIDs
     * @param userIDs ids of users which the analytics are requested for
     * @return map of analytics where the key is the user id and the value is the analytics mapped for
     * that user, if there are no analytics or IOException had happened empty map will be returned instead
     */
    suspend fun getUserAnalytics(userAuth: Auth, userIDs: List<Long>): Map<Long, Analytics>

}
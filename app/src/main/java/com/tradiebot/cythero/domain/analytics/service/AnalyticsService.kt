package com.tradiebot.cythero.domain.analytics.service

import com.tradiebot.cythero.domain.analytics.model.Analytics
import com.tradiebot.cythero.domain.auth.model.Auth

interface AnalyticsService {

    /**
     * returns user analytics for the users with the given userIDS but the given userAuth needs to
     * <b>have privileges</b> to do so first.
     *
     * For example an administrator will be able to see the analytics of multiple users so he will
     * be able to request multiple id's while a normal user will probably be only be able to request
     * analytics only for himself
     *
     * @param userAuth userAuth which has privileges to request analytics for the given userIDs
     * @param userIDs ids of users which the analytics are requested for
     * @return analytics for the requested users
     */
    suspend fun getUserAnalytics(userAuth: Auth, userIDs: List<Long>): List<Analytics>

}
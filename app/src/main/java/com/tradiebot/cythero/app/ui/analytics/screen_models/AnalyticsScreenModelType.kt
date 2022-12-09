package com.tradiebot.cythero.app.ui.analytics.screen_models

import com.tradiebot.cythero.app.ui.analytics.AnalyticsType

interface AnalyticsScreenModelType{

    /**
     * Function that defines that the current interface is no longer in focus, example of that
     * can be the user calling different report type.
     */
    fun requestedDifferentAnalytics()

    fun getReportType(): AnalyticsType

}
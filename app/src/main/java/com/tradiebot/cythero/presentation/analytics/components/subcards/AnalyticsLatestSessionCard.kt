package com.tradiebot.cythero.presentation.analytics.components.subcards

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsCard
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsContentHelper
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsPairField

@Composable
fun AnalyticsLatestSessionCard(
    state: AnalyticsScreenState.Success,
){
    val analyticsTable = state.userAnalytics[state.auth.user.id]!!.analyticsTable

    val lastPart = analyticsTable.part.last()
    val lastGrade = analyticsTable.grade.last()
    val lastPaintUsed = "${AnalyticsContentHelper.shouldIncludeDecimals(analyticsTable.totalPaintUsedMilliliters.last().toFloat())} ml"

    val lastTimePlayedSec = analyticsTable.totalTimePlayedSec.last().toLong()
    val lastTimePlayedString = AnalyticsContentHelper.generateStringFromTimePlayed(timePlayedSec = lastTimePlayedSec)

    AnalyticsCard(
        title = stringResource(R.string.field_latest_session),
    ) {
        AnalyticsPairField(
            key = stringResource(R.string.field_part),
            value = lastPart
        )

        AnalyticsPairField(
            key = stringResource(R.string.field_grade),
            value = lastGrade
        )

        AnalyticsPairField(
            key = stringResource(R.string.field_coverage),
            value = "TO DO"
        )

        AnalyticsPairField(
            key = stringResource(R.string.field_paint_used),
            value = lastPaintUsed
        )

        AnalyticsPairField(
            key = stringResource(R.string.field_time_taken),
            value = lastTimePlayedString
        )
    }
}

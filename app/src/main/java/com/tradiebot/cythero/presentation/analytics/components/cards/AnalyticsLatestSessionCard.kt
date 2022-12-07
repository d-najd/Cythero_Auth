package com.tradiebot.cythero.presentation.analytics.components.cards

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsContentHelper
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsPairField

@Composable
fun AnalyticsLatestSessionCard(
    state: AnalyticsScreenState.Success,
){
    val analyticsTable = state.userAnalytics[state.auth.user.id]!!.analyticsTable

    val lastPart = analyticsTable.part.lastOrNull() ?: ""
    val lastGrade = analyticsTable.grade.lastOrNull() ?: ""
    val lastCoverage = "${
        AnalyticsContentHelper.shouldIncludeDecimals(
                                ((analyticsTable.clearLowCoverage.lastOrNull() ?: 0.0) +
                                (analyticsTable.clearGoodCoverage.lastOrNull() ?: 0.0) +
                                (analyticsTable.clearHighCoverage.lastOrNull() ?: 0.0))/3.0)} ml"
    val lastPaintUsed = "${AnalyticsContentHelper.shouldIncludeDecimals(analyticsTable.totalPaintUsedMilliliters.lastOrNull() ?: 0.0)} ml"

    val lastTimePlayedSec = analyticsTable.totalTimePlayedSec.lastOrNull()?.toLong() ?: 0L
    val lastTimePlayedString = AnalyticsContentHelper.generateStringFromTimePlayed(timePlayedSec = lastTimePlayedSec)

    CytheroCard(
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
            value = lastCoverage
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

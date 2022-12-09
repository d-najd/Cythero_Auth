package com.tradiebot.cythero.presentation.analytics.components.reports.user.cards

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsUserScreenState
import com.tradiebot.cythero.domain.analytics.GradeEnum
import com.tradiebot.cythero.domain.analytics.PartEnum
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsContentHelper
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsPairField
import com.tradiebot.cythero.util.includeDecimals

@Composable
fun AnalyticsLatestSessionCard(
    state: AnalyticsUserScreenState.Success,
) {
    val analyticsTable = state.analytics[state.auth.user.id]!!.analyticsUserTable

    val lastPart = analyticsTable.part.lastOrNull() ?: PartEnum.NAN
    val lastGrade = analyticsTable.grade.lastOrNull() ?: GradeEnum.NAN
    val lastCoverage = "${((
            (
                (analyticsTable.clearLowCoverage.lastOrNull() ?: 0.0) + 
                (analyticsTable.clearGoodCoverage.lastOrNull() ?: 0.0) +
                (analyticsTable.clearHighCoverage.lastOrNull() ?: 0.0)) / 3.0)).includeDecimals(1)
    }%"
    val lastPaintUsed =
        "${AnalyticsContentHelper.shouldIncludeDecimals(analyticsTable.totalPaintUsedMilliliters.lastOrNull() ?: 0.0)} ml"

    val lastTimePlayedSec = analyticsTable.totalTimePlayedSec.lastOrNull()?.toLong() ?: 0L
    val lastTimePlayedString =
        AnalyticsContentHelper.generateStringFromTimePlayed(timePlayedSec = lastTimePlayedSec)

    CytheroCard(
        title = stringResource(R.string.field_latest_session),
    ) {
        AnalyticsPairField(
            key = stringResource(R.string.field_part),
            value = stringResource(lastPart.nameId)
        )

        AnalyticsPairField(
            key = stringResource(R.string.field_grade),
            value = stringResource(lastGrade.nameId)
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

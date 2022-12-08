package com.tradiebot.cythero.presentation.analytics.components.reports.user.cards

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsUserReportScreenState
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsContentHelper
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsPairField

@Composable
fun AnalyticsGeneralCard(
    state: AnalyticsUserReportScreenState.Success,
){
    val analyticsTable = state.userAnalytics[state.auth.user.id]!!.userAnalyticsTable

    val username = state.auth.user.username!!
    val timesPlayed = analyticsTable.sessionID.size.toString()
    val mostPaintedPart = if(analyticsTable.part.isNotEmpty()) analyticsTable.part.groupingBy { it }.eachCount().maxBy { it.value }.key else "Nan"
    val totalTimePlayedSec = if(analyticsTable.totalTimePlayedSec.isNotEmpty()) analyticsTable.totalTimePlayedSec.sum().toLong() else 0L
    val totalTimePlayedString = AnalyticsContentHelper.generateStringFromTimePlayed(timePlayedSec = totalTimePlayedSec)

    CytheroCard(
        title = stringResource(R.string.field_general),
    ) {
        AnalyticsPairField(
            key = stringResource(R.string.field_user),
            value = username
        )

        AnalyticsPairField(
            key = stringResource(R.string.field_times_played),
            value = timesPlayed
        )

        AnalyticsPairField(
            key = stringResource(R.string.field_most_painted_part),
            value = mostPaintedPart
        )

        AnalyticsPairField(
            key = stringResource(R.string.field_total_time_played),
            value = totalTimePlayedString
        )

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .padding(top = 12.dp),
        ) {
            Text(text = stringResource(R.string.action_export_to_pdf))
        }
    }
}
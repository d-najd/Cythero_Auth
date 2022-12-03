package com.tradiebot.cythero.presentation.analytics.components.subcards

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.presentation.analytics.components.ANALYTICS_CARD_CONTENT_PADDING
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsCard
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsPairField

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AnalyticsGeneralCard(
    state: AnalyticsScreenState.Success,
){
    val cardContentPadding = ANALYTICS_CARD_CONTENT_PADDING
    val analyticsTable = state.userAnalytics[state.auth.user.id]!!.analyticsTable

    val username = state.auth.user.firstName!!
    val timesPlayed = analyticsTable.sessionID.size.toString()
    val mostPaintedPart = analyticsTable.part.groupingBy { it }.eachCount().maxBy { it.value }.key
    val totalTimePlayedSec = analyticsTable.totalTimePlayedSec.sum().toLong()
    val totalTimePlayedString = if (totalTimePlayedSec >= 3600){
        "${totalTimePlayedSec/3600} ${pluralStringResource(id = R.plurals.hours, count = (totalTimePlayedSec/3600).toInt())}"
    } else if (totalTimePlayedSec >= 60) {
        "${totalTimePlayedSec/60} ${pluralStringResource(id = R.plurals.minutes, count = (totalTimePlayedSec/60).toInt())}"
    } else {
        "$totalTimePlayedSec ${pluralStringResource(id = R.plurals.hours, count = totalTimePlayedSec.toInt())}"
    }

    AnalyticsCard(
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
                .padding(cardContentPadding)
                .padding(top = 8.dp),
        ) {
            Text(text = stringResource(R.string.action_export_to_pdf))
        }
    }
}
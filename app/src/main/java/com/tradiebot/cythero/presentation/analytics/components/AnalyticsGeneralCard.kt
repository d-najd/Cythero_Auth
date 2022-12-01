package com.tradiebot.cythero.presentation.analytics.components

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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun AnalyticsGeneralCard(
    state: AnalyticsScreenState.Success,
){
    val cardContentPadding = ANALYTICS_CARD_CONTENT_PADDING

    val username = state.auth.user.firstName!!
    val analyticsTable = state.userAnalytics[state.auth.user.id]!!.analyticsTable
    val timesPlayed = analyticsTable.sessionID.size.toString()
    val mostPaintedPart = analyticsTable.part.groupingBy { it }.eachCount().maxBy { it.value }.key
    val totalTimePlayedSec = analyticsTable.totalTimePlayedSec.sum().toLong()
    val totalTimePlayedString = if (totalTimePlayedSec >= 3600){
        "${totalTimePlayedSec/3600}  ${pluralStringResource(id = R.plurals.hours, count = (totalTimePlayedSec/3600).toInt())}"
    } else if (totalTimePlayedSec >= 60) {
        "${totalTimePlayedSec/60}  ${pluralStringResource(id = R.plurals.minutes, count = (totalTimePlayedSec/60).toInt())}"
    } else {
        "$totalTimePlayedSec  ${pluralStringResource(id = R.plurals.hours, count = totalTimePlayedSec.toInt())}"
    }

    AnalyticsCard(
        title = stringResource(R.string.field_general),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                text = "${stringResource(R.string.field_user)}:",
                modifier = Modifier
                    .padding(cardContentPadding),
            )
            Text(
                text = username,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(cardContentPadding)
                    .padding(top = 2.dp) // the text on the left is a bit lower
                    .fillMaxWidth()
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                text = "${stringResource(R.string.field_times_played)}:",
                modifier = Modifier
                    .padding(cardContentPadding),
            )
            Text(
                text = timesPlayed,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(cardContentPadding)
                    .padding(top = 2.dp) // the text on the left is a bit lower
                    .fillMaxWidth()
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                text = "${stringResource(R.string.field_most_painted_part)}:",
                modifier = Modifier
                    .padding(cardContentPadding),
            )
            Text(
                text = mostPaintedPart,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(cardContentPadding)
                    .padding(top = 2.dp) // the text on the left is a bit lower
                    .fillMaxWidth()
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                text = "${stringResource(R.string.field_total_time_played)}:",
                modifier = Modifier
                    .padding(cardContentPadding),
            )
            Text(
                text = totalTimePlayedString,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(cardContentPadding)
                    .padding(top = 2.dp) // the text on the left is a bit lower
                    .fillMaxWidth()
            )
        }

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
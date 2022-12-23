package com.tradiebot.cythero.presentation.analytics.components.reports.user.cards

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.domain.analytics.Part
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsPairField
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.components.dialogs.CytheroButtonDefaults
import com.tradiebot.cythero.util.CytheroDateFormat

@Composable
fun AnalyticsGeneralCard(
    state: AnalyticsScreenState.UserSuccess,
){
    val analyticsTable = state.analytics[state.auth.user.id]!!.analyticsUserTable

    val username = state.auth.user.username!!
    val timesPlayed = analyticsTable.sessionID.size.toString()
    val mostPaintedPart = if(analyticsTable.part.isNotEmpty()) analyticsTable.part.groupingBy { it }.eachCount().maxBy { it.value }.key else Part.DOOR
    val totalTimePlayedSec = if(analyticsTable.totalTimePlayedSec.isNotEmpty()) analyticsTable.totalTimePlayedSec.sum().toLong() else 0L
    val totalTimePlayedString = CytheroDateFormat.generateStringFromTime(timeSeconds = totalTimePlayedSec)

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
            value = stringResource(mostPaintedPart.nameId)
        )

        AnalyticsPairField(
            key = stringResource(R.string.field_total_time_played),
            value = totalTimePlayedString
        )

        Button(
            onClick = { },
            shape = RoundedCornerShape(CytheroButtonDefaults.BUTTON_CORNER_ROUNDING),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .padding(top = 12.dp),
        ) {
            Text(text = stringResource(R.string.action_export_to_pdf))
        }
    }
}
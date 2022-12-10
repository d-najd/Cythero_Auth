package com.tradiebot.cythero.presentation.analytics.components.reports.part

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsPartScreenState
import com.tradiebot.cythero.domain.analytics.CoverageType
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.components.ScrollableHorizontalItem

@Composable
fun AnalyticsPartReportContent(
    state: AnalyticsPartScreenState.Success,
    contentPadding: PaddingValues,
) {
    var selectedCoverageType by remember { mutableStateOf(CoverageType.OVERALL) }

    AnalyticsPartGradesBreakdownCard(
        state = state,
        selectedCoverageType = selectedCoverageType,
        onSelectedCoverageTypeChange = { selectedCoverageType = it }
    )

    ScrollableHorizontalItem {
        CytheroCard(
            modifier = Modifier
                .height(400.dp)
                .width(750.dp),
            title = stringResource(R.string.field_time_taken_last_sessions),
        ) {
            /*
            val dataSet = LineChartHelper.generatePartsDataSet(state.analytics[state.auth.user.id]!!)

            LineChart(
                dataSets = dataSet,
            )
             */
        }
    }


    Divider(
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 16.dp)
    )
}

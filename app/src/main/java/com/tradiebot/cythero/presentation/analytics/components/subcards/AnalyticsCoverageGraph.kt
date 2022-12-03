package com.tradiebot.cythero.presentation.analytics.components.subcards

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsCard
import com.tradiebot.cythero.presentation.components.ScrollableHorizontalRow

@Composable
fun AnalyticsCoverageGraph(
    state: AnalyticsScreenState.Success,
) {

    ScrollableHorizontalRow {
        AnalyticsCard(
            modifier = Modifier,
            title = stringResource(R.string.field_coverage_graph_last_sessions),
        ) {

        }
    }
}

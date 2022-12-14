package com.tradiebot.cythero.presentation.analytics.components.reports.user

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsNoContentText
import com.tradiebot.cythero.presentation.analytics.components.reports.user.cards.AnalyticsCoverageGraph
import com.tradiebot.cythero.presentation.analytics.components.reports.user.cards.AnalyticsGeneralCard
import com.tradiebot.cythero.presentation.analytics.components.reports.user.cards.AnalyticsGradeCard
import com.tradiebot.cythero.presentation.analytics.components.reports.user.cards.AnalyticsLatestSessionCard

@Composable
fun AnalyticsUserReportContent(
    state: AnalyticsScreenState.UserSuccess,
) {
    if(state.analytics.isEmpty()) {
        AnalyticsNoContentText(stringResource(R.string.info_no_data_analytics_user))
    } else {
        AnalyticsGeneralCard(
            state = state,
        )
    
        AnalyticsGradeCard(
            state = state,
        )
    
        AnalyticsLatestSessionCard(
            state = state,
        )
    
        AnalyticsCoverageGraph(
            state = state
        )
    
        Divider(
            modifier = Modifier.padding(
                horizontal = 4.dp,
                vertical = 16.dp
            )
        )
    }
}
package com.tradiebot.cythero.presentation.analytics

import androidx.activity.compose.BackHandler
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsPartScreenState
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsReportTypeScreenState
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsUserScreenState
import com.tradiebot.cythero.domain.analytics.PartEnum
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsContent
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsScreen(
    reportTypeState: AnalyticsReportTypeScreenState.Success,
    userReportState: AnalyticsUserScreenState,
    partReportState: AnalyticsPartScreenState,
    onBackClicked: () -> Unit,

    //Report Type Content
    onGenerateUserReportClicked: (Pair<Date, Date>) -> Unit,
    onGeneratePartReportClicked: (PartEnum) -> Unit,
) {
    Scaffold { contentPadding ->
        BackHandler{ onBackClicked() }

        AnalyticsContent (
            reportTypeState = reportTypeState,
            userReportState = userReportState,
            partReportState = partReportState,
            contentPadding = contentPadding,
            onGenerateUserReportClicked = onGenerateUserReportClicked,
            onGeneratePartReportClicked = onGeneratePartReportClicked,
        )
    }
}

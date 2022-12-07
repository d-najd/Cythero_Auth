package com.tradiebot.cythero.presentation.analytics

import androidx.activity.compose.BackHandler
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreen
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsReportTypeScreenState
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsUserReportScreenState
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsScreen(
    reportTypeState: AnalyticsReportTypeScreenState.Success,
    userReportState: AnalyticsUserReportScreenState,
    onBackClicked: () -> Unit,

    //Report Type Content
    onGenerateReportClicked: (AnalyticsScreen.SelectedReportType) -> Unit,
) {
    Scaffold { contentPadding ->
        BackHandler{ onBackClicked() }

        AnalyticsContent(
            reportTypeState = reportTypeState,
            userReportState = userReportState,
            contentPadding = contentPadding,
            onGenerateReportClicked = onGenerateReportClicked,
        )
    }
}

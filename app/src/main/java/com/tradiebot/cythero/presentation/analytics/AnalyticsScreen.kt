package com.tradiebot.cythero.presentation.analytics

import androidx.activity.compose.BackHandler
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreen
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsReportTypeScreenState
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsUserReportScreenState
import com.tradiebot.cythero.domain.analytics.part.model.AnalyticsPart
import com.tradiebot.cythero.domain.analytics.part.model.AnalyticsParts
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsContent
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsScreen(
    reportTypeState: AnalyticsReportTypeScreenState.Success,
    userReportState: AnalyticsUserReportScreenState,
    onBackClicked: () -> Unit,

    //Report Type Content
    onGenerateUserReportClicked: (AnalyticsScreen.SelectedReportType, Pair<Date, Date>) -> Unit,
) {
    Scaffold { contentPadding ->
        BackHandler{ onBackClicked() }

        var test = AnalyticsParts.mockInstance()

        AnalyticsContent(
            reportTypeState = reportTypeState,
            userReportState = userReportState,
            contentPadding = contentPadding,
            onGenerateUserReportClicked = onGenerateUserReportClicked,
        )
    }
}

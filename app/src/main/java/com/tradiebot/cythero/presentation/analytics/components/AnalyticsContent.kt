package com.tradiebot.cythero.presentation.analytics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.domain.analytics.Part
import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUsageSortType
import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUsageSortable
import com.tradiebot.cythero.presentation.analytics.components.reports.part.AnalyticsPartReportContent
import com.tradiebot.cythero.presentation.analytics.components.reports.usage.AnalyticsUsageReportContent
import com.tradiebot.cythero.presentation.analytics.components.reports.user.AnalyticsUserReportContent
import com.tradiebot.cythero.presentation.components.LoadingScreen
import com.tradiebot.cythero.util.convertPixelsToDp
import java.util.*

@Composable
fun AnalyticsContent(
    state: AnalyticsScreenState,
    contentPadding: PaddingValues,
    onGenerateUserReportClicked: (Pair<Date, Date>) -> Unit,
    onGeneratePartReportClicked: (Part) -> Unit,
    
    // Usage
    onGenerateUsageReportClicked: (Pair<Date, Date>) -> Unit,
    onSortUsageReport: (AnalyticsUsageSortType, Boolean) -> Unit,
    onShowUsageItemInfo: (AnalyticsUsageSortable) -> Unit,
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .offset(y = LocalContext.current.convertPixelsToDp(scrollState.value.toFloat()).dp * -1)
            .fillMaxWidth()
            .height(175.dp)
            .background(MaterialTheme.colorScheme.primary)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            //.padding(vertical = 24.dp)
            .padding(contentPadding)
            .verticalScroll(scrollState)
            .fillMaxSize(),
    ) {

        AnalyticsSelectAnalyticsCard(
            onGenerateUserReportClicked = onGenerateUserReportClicked,
            onGeneratePartReportClicked = onGeneratePartReportClicked,
            onGenerateUsageReportClicked = onGenerateUsageReportClicked,
        )

        when (state) {
            is AnalyticsScreenState.Loading -> throw IllegalStateException("How did we get here?")
            is AnalyticsScreenState.LoadingType -> LoadingScreen()
            is AnalyticsScreenState.Success -> {}
            is AnalyticsScreenState.UserSuccess -> {
                AnalyticsUserReportContent(
                    state = state,
                )
            }
            is AnalyticsScreenState.PartSuccess -> {
                AnalyticsPartReportContent(
                    state = state,
                )
            }
            is AnalyticsScreenState.UsageSuccess -> {
                AnalyticsUsageReportContent(
                    state = state,
                    onSortUsageReport = onSortUsageReport,
                    onShowUsageItemInfo = onShowUsageItemInfo,
                )
            }
        }
    }
}


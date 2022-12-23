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
import com.tradiebot.cythero.domain.analytics.CoverageType
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
    
    // User
    onGenerateUserReportClicked: (Pair<Date, Date>) -> Unit,
    
    // Part
    onGeneratePartReportClicked: (Part) -> Unit,
    onUpdatePartSelectedCoverageType: (CoverageType) -> Unit,
    
    // Usage
    onGenerateUsageReportClicked: (Pair<Date, Date>) -> Unit,
    onUpdateUsageScreenIndex: (Boolean) -> Unit,
    onSortUsageReport: (AnalyticsUsageSortType, Boolean) -> Unit,
    onShowUsageItemInfoDialog: (AnalyticsUsageSortable) -> Unit,
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
                    onUpdatePartSelectedCoverageType = onUpdatePartSelectedCoverageType
                )
            }
            is AnalyticsScreenState.UsageSuccess -> {
                AnalyticsUsageReportContent(
                    state = state,
                    onUpdateUsageScreenIndex = onUpdateUsageScreenIndex,
                    onSortUsageReport = onSortUsageReport,
                    onShowUsageItemInfoDialog = onShowUsageItemInfoDialog,
                )
            }
        }
    }
}


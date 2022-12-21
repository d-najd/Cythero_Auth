package com.tradiebot.cythero.presentation.analytics

import androidx.activity.compose.BackHandler
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.domain.analytics.Part
import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUsageSortType
import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUsageSortable
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsContent
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsScreenContent(
    presenter: AnalyticsScreenState,
    onBackClicked: () -> Unit,
    onGenerateUserReportClicked: (Pair<Date, Date>) -> Unit,
    onGeneratePartReportClicked: (Part) -> Unit,
    
    // Usage
    onGenerateUsageReportClicked: (Pair<Date, Date>) -> Unit,
    onSortUsageReport: (AnalyticsUsageSortType, Boolean) -> Unit,
    onShowUsageItemInfo: (AnalyticsUsageSortable) -> Unit,
) {
    Scaffold { contentPadding ->
        BackHandler { onBackClicked() }
        
        AnalyticsContent(
            state = presenter,
            contentPadding = contentPadding,
            onGenerateUserReportClicked = onGenerateUserReportClicked,
            onGeneratePartReportClicked = onGeneratePartReportClicked,
            onGenerateUsageReportClicked = onGenerateUsageReportClicked,
            onSortUsageReport = onSortUsageReport,
            onShowUsageItemInfo = onShowUsageItemInfo,
        )
    }
}

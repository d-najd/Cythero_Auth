package com.tradiebot.cythero.presentation.analytics.components.reports.usage

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUsageSortType
import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUsageSortable
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsNoContentText
import com.tradiebot.cythero.presentation.analytics.components.reports.usage.components.AnalyticsUsageChangeScreenContent
import com.tradiebot.cythero.presentation.analytics.components.reports.usage.components.AnalyticsUsageReportCard
import com.tradiebot.cythero.presentation.analytics.components.reports.usage.components.AnalyticsUsageReportLabels
import com.tradiebot.cythero.presentation.components.CytheroHorizontallyScrollableColumn

@Composable
fun AnalyticsUsageReportContent(
	state: AnalyticsScreenState.UsageSuccess,
	onUpdateUsageScreenIndex: (Boolean) -> Unit,
	onSortUsageReport: (AnalyticsUsageSortType, Boolean) -> Unit,
	onShowUsageItemInfoDialog: (AnalyticsUsageSortable) -> Unit,
) {
	val analytics = state.analytics
	if(analytics.analyticsList.isEmpty()) {
		AnalyticsNoContentText(stringResource(R.string.info_no_data_analytics_usage))
	} else {
		val analyticsSublist = analytics.analyticsList.subList(
			fromIndex = (state.screenIndex * 10) - 10,
			toIndex = minOf(
				analytics.analyticsList.size,
				state.screenIndex * 10
			)
		)
		
		val reportWidth = 800.dp
		val numOfFields = 5
		
		CytheroHorizontallyScrollableColumn(
			modifier = Modifier
				.width(reportWidth)
				.fillMaxHeight()
		) {
			Column(
				modifier = Modifier.padding(
					horizontal = 24.dp,
					vertical = 12.dp
				),
			) {
				Row(
					modifier = Modifier
						.padding(
							start = 8.dp,
							end = 8.dp
						)
				) {
					AnalyticsUsageReportLabels(
						analytics = analytics,
						width = reportWidth / numOfFields,
						onSortUsageReport = onSortUsageReport,
					)
				}
				
				Divider(
					modifier = Modifier
						.padding(vertical = 2.dp)
						.fillMaxWidth()
				)
				
				Column {
					for (analytic in analyticsSublist) {
						AnalyticsUsageReportCard(
							analytic = analytic,
							width = reportWidth / numOfFields,
							onShowUsageItemInfo = onShowUsageItemInfoDialog,
						)
					}
				}
			}
		}
		
		AnalyticsUsageChangeScreenContent(
			state = state,
			onUpdateUsageScreenIndex = onUpdateUsageScreenIndex
		)
	}
}


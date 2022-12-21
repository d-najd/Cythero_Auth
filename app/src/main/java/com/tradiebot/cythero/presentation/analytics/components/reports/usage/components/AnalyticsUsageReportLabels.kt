package com.tradiebot.cythero.presentation.analytics.components.reports.usage.components

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.Dp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUsageSortType
import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUsageSortableHolder
import com.tradiebot.cythero.presentation.analytics.components.reports.usage.AnalyticsUsageTriStateRow
import com.tradiebot.cythero.presentation.analytics.components.reports.usage.analyticsUsageToggleableStateHelper

@Composable
fun AnalyticsUsageReportLabels(
	analytics: AnalyticsUsageSortableHolder,
	width: Dp,
	onSortUsageReport: (AnalyticsUsageSortType, Boolean) -> Unit,
) {
	AnalyticsUsageTriStateRow(
		modifier = Modifier.width(width),
		state = analyticsUsageToggleableStateHelper(
			analytics = analytics,
			type = AnalyticsUsageSortType.USER
		),
		text = stringResource(R.string.field_user),
		onClick = { prevState ->
			if(prevState == ToggleableState.On) {
				onSortUsageReport(AnalyticsUsageSortType.USER, false)
			} else onSortUsageReport(AnalyticsUsageSortType.USER, true)
		}
	)
	AnalyticsUsageTriStateRow(
		modifier = Modifier.width(width),
		state = analyticsUsageToggleableStateHelper(
			analytics = analytics,
			type = AnalyticsUsageSortType.PART
		),
		text = stringResource(R.string.field_part),
		onClick = { prevState ->
			if(prevState == ToggleableState.On) {
				onSortUsageReport(AnalyticsUsageSortType.PART, false)
			} else onSortUsageReport(AnalyticsUsageSortType.PART, true)
		}
	)
	AnalyticsUsageTriStateRow(
		modifier = Modifier.width(width),
		state = analyticsUsageToggleableStateHelper(
			analytics = analytics,
			type = AnalyticsUsageSortType.DATE
		),
		text = stringResource(R.string.field_date),
		onClick = { prevState ->
			if(prevState == ToggleableState.On) {
				onSortUsageReport(AnalyticsUsageSortType.DATE, false)
			} else onSortUsageReport(AnalyticsUsageSortType.DATE, true)
		}
	)
	AnalyticsUsageTriStateRow(
		modifier = Modifier.width(width),
		state = analyticsUsageToggleableStateHelper(
			analytics = analytics,
			type = AnalyticsUsageSortType.PAINT_USED
		),
		text = stringResource(R.string.field_paint_used),
		onClick = { prevState ->
			if(prevState == ToggleableState.On) {
				onSortUsageReport(AnalyticsUsageSortType.PAINT_USED, false)
			} else onSortUsageReport(AnalyticsUsageSortType.PAINT_USED, true)
		}
	)
	AnalyticsUsageTriStateRow(
		modifier = Modifier.width(width),
		state = analyticsUsageToggleableStateHelper(
			analytics = analytics,
			type = AnalyticsUsageSortType.TOTAL_TIME_SPENT
		),
		text = stringResource(R.string.field_total_time_spent),
		onClick = { prevState ->
			if(prevState == ToggleableState.On) {
				onSortUsageReport(AnalyticsUsageSortType.TOTAL_TIME_SPENT, false)
			} else onSortUsageReport(AnalyticsUsageSortType.TOTAL_TIME_SPENT, true)
		}
	)
}
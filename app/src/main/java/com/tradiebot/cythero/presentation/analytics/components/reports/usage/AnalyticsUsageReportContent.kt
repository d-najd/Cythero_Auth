package com.tradiebot.cythero.presentation.analytics.components.reports.usage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.NavigateBefore
import androidx.compose.material.icons.rounded.NavigateNext
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUsageSortType
import com.tradiebot.cythero.presentation.components.ScrollableHorizontalItem
import com.tradiebot.cythero.util.CytheroDateFormat
import kotlin.math.roundToInt

@OptIn(ExperimentalUnitApi::class)
@Composable
fun AnalyticsUsageReportContent(
	state: AnalyticsScreenState.UsageSuccess,
	sortUsageReport: (AnalyticsUsageSortType, Boolean) -> Unit,
) {
	val analytics = state.analytics
	
	// The index of the selected screen in the report
	var screenIndex by remember { mutableStateOf(1) }
	
	val analyticsSublist = analytics.analyticsList.subList(
		fromIndex = (screenIndex * 10) - 10,
		toIndex = minOf(analytics.analyticsList.size, screenIndex * 10)
	)
	
	val width = 800.dp
	val numOfFields = 5
	
	ScrollableHorizontalItem(
		modifier = Modifier
			.width(width)
			.fillMaxHeight()
	) {
		Column(
			modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
		) {
			Row(
				modifier = Modifier
					.padding(start = 8.dp, end = 8.dp)
			) {
				AnalyticsUsageTriStateRow(
					modifier = Modifier.width(width / numOfFields),
					state = analyticsUsageToggleableStateHelper(
						analytics = analytics,
						type = AnalyticsUsageSortType.USER
					),
					text = stringResource(R.string.field_user),
					onClick = { prevState ->
						if(prevState == ToggleableState.On) {
							sortUsageReport(AnalyticsUsageSortType.USER, false)
						} else sortUsageReport(AnalyticsUsageSortType.USER, true)
					}
				)
				AnalyticsUsageTriStateRow(
					modifier = Modifier.width(width / numOfFields),
					state = analyticsUsageToggleableStateHelper(
						analytics = analytics,
						type = AnalyticsUsageSortType.PART
					),
					text = stringResource(R.string.field_part),
					onClick = { prevState ->
						if(prevState == ToggleableState.On) {
							sortUsageReport(AnalyticsUsageSortType.PART, false)
						} else sortUsageReport(AnalyticsUsageSortType.PART, true)
					}
				)
				AnalyticsUsageTriStateRow(
					modifier = Modifier.width(width / numOfFields),
					state = analyticsUsageToggleableStateHelper(
						analytics = analytics,
						type = AnalyticsUsageSortType.DATE
					),
					text = stringResource(R.string.field_date),
					onClick = { prevState ->
						if(prevState == ToggleableState.On) {
							sortUsageReport(AnalyticsUsageSortType.DATE, false)
						} else sortUsageReport(AnalyticsUsageSortType.DATE, true)
					}
				)
				AnalyticsUsageTriStateRow(
					modifier = Modifier.width(width / numOfFields),
					state = analyticsUsageToggleableStateHelper(
						analytics = analytics,
						type = AnalyticsUsageSortType.PAINT_USED
					),
					text = stringResource(R.string.field_paint_used),
					onClick = { prevState ->
						if(prevState == ToggleableState.On) {
							sortUsageReport(AnalyticsUsageSortType.PAINT_USED, false)
						} else sortUsageReport(AnalyticsUsageSortType.PAINT_USED, true)
					}
				)
				AnalyticsUsageTriStateRow(
					modifier = Modifier.width(width / numOfFields),
					state = analyticsUsageToggleableStateHelper(
						analytics = analytics,
						type = AnalyticsUsageSortType.TOTAL_TIME_SPENT
					),
					text = stringResource(R.string.field_total_time_spent),
					onClick = { prevState ->
						if(prevState == ToggleableState.On) {
							sortUsageReport(AnalyticsUsageSortType.TOTAL_TIME_SPENT, false)
						} else sortUsageReport(AnalyticsUsageSortType.TOTAL_TIME_SPENT, true)
					}
				)
			}
			
			Divider(
				modifier = Modifier
					.padding(vertical = 2.dp)
					.fillMaxWidth()
			)
			
			Column {
				for(analytic in analyticsSublist) {
					Card(
						modifier = Modifier
							.padding(vertical = 2.dp)
							.height(50.dp),
						shape = RoundedCornerShape(6.dp),
					) {
						Row(
							modifier = Modifier
								.padding(vertical = 8.dp, horizontal = 8.dp)
								.fillMaxHeight()
								.background(MaterialTheme.colorScheme.surfaceVariant),
							verticalAlignment = Alignment.CenterVertically,
						) {
							Text(
								modifier = Modifier
									.width(width / numOfFields),
								color = MaterialTheme.colorScheme.onSurfaceVariant,
								text = analytic.user,
							)
							Text(
								modifier = Modifier
									.width(width / numOfFields),
								color = MaterialTheme.colorScheme.onSurfaceVariant,
								text = stringResource(analytic.part.nameId),
							)
							Text(
								modifier = Modifier
									.width(width / numOfFields),
								color = MaterialTheme.colorScheme.onSurfaceVariant,
								text = CytheroDateFormat.defaultRequestDateFormat().format(analytic.date),
							)
							Text(
								modifier = Modifier
									.width(width / numOfFields),
								color = MaterialTheme.colorScheme.onSurfaceVariant,
								text = analytic.paintUsedMl.toString(),
							)
							Text(
								modifier = Modifier
									.width(width / numOfFields),
								color = MaterialTheme.colorScheme.onSurfaceVariant,
								text = analytic.totalTimeSpentMin.toString(),
							)
						}
					}
				}
			}
		}
	}
	
	Row(
		modifier = Modifier
			.fillMaxWidth(),
		horizontalArrangement = Arrangement.Center,
		verticalAlignment = Alignment.CenterVertically,
	) {
		IconButton(
			onClick = {
					  if(screenIndex > 1){
						  screenIndex--
					  }
			},
		) {
			Icon(
				imageVector = Icons.Rounded.NavigateBefore,
				contentDescription = ""
			)
		}
		
		Text(
			modifier = Modifier
				.padding(horizontal = 12.dp),
			text = "$screenIndex/${(analytics.analyticsList.size/10f).roundToInt()}",
			color = MaterialTheme.colorScheme.onSurfaceVariant,
			textAlign = TextAlign.Center,
			fontWeight = FontWeight.Bold,
			fontSize = TextUnit(16f, TextUnitType.Sp),
		)
		
		IconButton(
			onClick = {
					  if(screenIndex < (analytics.analyticsList.size/10f).roundToInt()) {
						  screenIndex++
					  }
			},
		) {
			Icon(
				imageVector = Icons.Rounded.NavigateNext,
				contentDescription = ""
			)
		}
	}
	
}

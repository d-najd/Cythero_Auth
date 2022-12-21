package com.tradiebot.cythero.presentation.analytics.components.reports.usage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUsageSortType
import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUsageSortable
import com.tradiebot.cythero.presentation.analytics.components.reports.usage.components.AnalyticsUsageReportLabels
import com.tradiebot.cythero.presentation.components.CytheroHorizontallyScrollableColumn
import com.tradiebot.cythero.util.CytheroDateFormat
import kotlin.math.roundToInt

@OptIn(ExperimentalUnitApi::class)
@Composable
fun AnalyticsUsageReportContent(
	state: AnalyticsScreenState.UsageSuccess,
	onSortUsageReport: (AnalyticsUsageSortType, Boolean) -> Unit,
	onShowUsageItemInfo: (AnalyticsUsageSortable) -> Unit,
) {
	val analytics = state.analytics
	
	// The index of the selected screen in the report
	var screenIndex by remember { mutableStateOf(1) }
	
	val analyticsSublist = analytics.analyticsList.subList(
		fromIndex = (screenIndex * 10) - 10,
		toIndex = minOf(analytics.analyticsList.size, screenIndex * 10)
	)
	
	val reportWidth = 800.dp
	val numOfFields = 5
	
	CytheroHorizontallyScrollableColumn(
		modifier = Modifier
			.width(reportWidth)
			.fillMaxHeight()
	) {
		Column(
			modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
		) {
			Row(
				modifier = Modifier
					.padding(start = 8.dp, end = 8.dp)
			) {
				AnalyticsUsageReportLabels(
					width = reportWidth,
					numOfFields = numOfFields,
					analytics = analytics,
					onSortUsageReport = onSortUsageReport,
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
								.background(MaterialTheme.colorScheme.surfaceVariant)
								.clickable { onShowUsageItemInfo(analytic) },
							verticalAlignment = Alignment.CenterVertically,
						) {
							AnalyticsUsageBoxText(
								modifier = Modifier.width(reportWidth/numOfFields) ,
								text = analytic.user,
							)
							AnalyticsUsageBoxText(
								modifier = Modifier.width(reportWidth/numOfFields) ,
								text = stringResource(analytic.part.nameId),
							)
							AnalyticsUsageBoxText(
								modifier = Modifier.width(reportWidth/numOfFields) ,
								text = CytheroDateFormat.defaultRequestDateFormat().format(analytic.date),
							)
							AnalyticsUsageBoxText(
								modifier = Modifier.width(reportWidth/numOfFields) ,
								text = analytic.paintUsedMl.toString(),
							)
							AnalyticsUsageBoxText(
								modifier = Modifier.width(reportWidth/numOfFields) ,
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



@Composable
private fun AnalyticsUsageBoxText(
	modifier: Modifier,
	text: String,
){
	Box(
		modifier = modifier
			.fillMaxHeight(),
		contentAlignment = Alignment.CenterStart
	){
		Text(
			modifier = modifier,
			color = MaterialTheme.colorScheme.onSurfaceVariant,
			text = text,
		)
	}
}

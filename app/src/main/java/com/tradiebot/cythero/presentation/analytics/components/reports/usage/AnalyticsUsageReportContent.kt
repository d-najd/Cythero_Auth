package com.tradiebot.cythero.presentation.analytics.components.reports.usage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUsageSortType
import com.tradiebot.cythero.presentation.components.ScrollableHorizontalItem

@Composable
fun AnalyticsUsageReportContent(
	state: AnalyticsScreenState.UsageSuccess,
	sortUsageReport: (AnalyticsUsageSortType, Boolean) -> Unit,
) {
	// The index of the selected screen in the report
	var screenIndex = remember { mutableStateOf(0) }
	
	val width = 700.dp
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
					.padding(bottom = 4.dp, start = 8.dp, end = 8.dp)
			) {
				Text(
					modifier = Modifier.width(width / numOfFields),
					text = stringResource(R.string.field_user)
				)
				Text(
					modifier = Modifier.width(width / numOfFields),
					text = stringResource(R.string.field_part)
				)
				Text(
					modifier = Modifier.width(width / numOfFields),
					text = stringResource(R.string.field_date)
				)
				Text(
					modifier = Modifier.width(width / numOfFields),
					text = stringResource(R.string.field_paint_used_ml)
				)
				Text(
					modifier = Modifier.width(width / numOfFields),
					text = stringResource(R.string.field_total_time_played)
				)
			}
			
			Divider(
				modifier = Modifier
					.padding(vertical = 2.dp)
					.fillMaxWidth()
			)
			
			LazyColumn(
				modifier = Modifier
					.height(500.dp)
			) {
				items(state.analytics) { analytic ->
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
								text = analytic.part,
							)
							Text(
								modifier = Modifier
									.width(width / numOfFields),
								color = MaterialTheme.colorScheme.onSurfaceVariant,
								text = analytic.date,
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
}

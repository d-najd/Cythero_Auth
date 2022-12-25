package com.tradiebot.cythero.presentation.analytics.components.reports.usage.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.github.mikephil.charting.data.PieDataSet
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.domain.analytics.Grade
import com.tradiebot.cythero.domain.analytics.shared.model.AnalyticSession
import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUsageSortable
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsPairField
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.components.chart.PieChart
import com.tradiebot.cythero.presentation.components.chart.PieChartHelper
import com.tradiebot.cythero.presentation.util.chart.ChartSettingsHolder
import kotlinx.coroutines.flow.flow

@Composable
fun AnalyticsUsageItemInfoDialog(
	state: AnalyticsScreenState.UsageSuccess,
	analyticUsage: AnalyticsUsageSortable,
	onDismissRequest: () -> Unit,
) {
	if (state.analyticsSessionInfo == null) throw IllegalStateException("Session info should not " +
		"be null when calling this dialog")
	
	AlertDialog(
		modifier = Modifier
			.fillMaxHeight()
			.padding(vertical = 24.dp),
		onDismissRequest = onDismissRequest,
		confirmButton = {
			Text(
				modifier = Modifier.clickable { onDismissRequest() },
				text = stringResource(R.string.action_close)
			)
		},
		dismissButton = { },
		title = {
			Text(text = stringResource(R.string.field_session_info))
		},
		text = {
			Column(
				Modifier.verticalScroll(rememberScrollState())
			) {
				Text(
					modifier = Modifier.align(Alignment.CenterHorizontally),
					text = "${analyticUsage.user}'s Session Card",
					color = MaterialTheme.colorScheme.onSurfaceVariant,
					textAlign = TextAlign.Center,
					fontWeight = FontWeight.Bold,
					fontSize = 16.sp,
				)
				
				Divider(
					modifier = Modifier.padding(vertical = 12.dp),
					color = Color.Transparent
				)
				
				val pieChartSettingsHolder = ChartSettingsHolder.defaultPieCSettings()
				pieChartSettingsHolder.rightOffset = -5f
				
				val timeSpentDataSet = generateTimeSpentDataSet(
					analyticSessionInfo = state.analyticsSessionInfo,
				)
				
				CytheroCard(
					title = stringResource(R.string.field_time_spent_seconds),
					modifier = Modifier.height(200.dp),
					applyPadding = false,
				) {
					PieChart(
						chartSettingsHolder = pieChartSettingsHolder,
						dataSet = flow { emit(timeSpentDataSet) }
					)
				}
				
				Divider(
					modifier = Modifier.padding(vertical = 12.dp),
					color = Color.Transparent
				)
				
				val colorUsedDataSet = generateColorUsedDataSet(
					analyticSessionInfo = state.analyticsSessionInfo,
				)
				
				CytheroCard(
					title = stringResource(R.string.field_color_used_mililiters),
					modifier = Modifier.height(200.dp),
					applyPadding = false,
				) {
					PieChart(
						chartSettingsHolder = pieChartSettingsHolder,
						dataSet = flow { emit(colorUsedDataSet) }
					)
				}
				
				Divider(
					modifier = Modifier.padding(vertical = 12.dp),
					color = Color.Transparent
				)
				
				CytheroCard(
					modifier = Modifier
						.width(500.dp)
						.fillMaxHeight()
						.horizontalScroll(rememberScrollState()),
					applyPadding = false,
				) {
					for (session in state.analyticsSessionInfo) {
						AnalyticsPairField(
							modifier = Modifier.width(375.dp),
							key = state.analyticsLabels.find { o -> o.id == session.labelId }?.name
								?: "",
							value = session.score
						)
					}
				}
			}
		}
	)
}

@Composable
private fun generateColorUsedDataSet(
	analyticSessionInfo: List<AnalyticSession>,
): PieDataSet {
	val primerColorUsed =
		analyticSessionInfo.firstOrNull { o -> o.labelId == 105 }?.score?.toFloatOrNull()
			?: 0f
	val baseColorUsed =
		analyticSessionInfo.firstOrNull { o -> o.labelId == 111 }?.score?.toFloatOrNull()
			?: 0f
	val clearColorUsed =
		analyticSessionInfo.firstOrNull { o -> o.labelId == 117 }?.score?.toFloatOrNull()
			?: 0f
	
	return PieChartHelper.generateDataSetPositive(
		data = listOf(
			Pair(primerColorUsed, stringResource(R.string.field_primer)),
			Pair(baseColorUsed, stringResource(R.string.field_base)),
			Pair(clearColorUsed, stringResource(R.string.field_clear)),
		),
		colors = listOf(
			Grade.A.rgb,
			Grade.B.rgb,
			Grade.C.rgb,
		)
	)
}

@Composable
private fun generateTimeSpentDataSet(
	analyticSessionInfo: List<AnalyticSession>,
): PieDataSet {
	val primerTimeSpent =
		analyticSessionInfo.firstOrNull { o -> o.labelId == 104 }?.score?.toFloatOrNull()
			?: 0f
	val baseTimeSpent =
		analyticSessionInfo.firstOrNull { o -> o.labelId == 110 }?.score?.toFloatOrNull()
			?: 0f
	val clearTimeSpent =
		analyticSessionInfo.firstOrNull { o -> o.labelId == 116 }?.score?.toFloatOrNull()
			?: 0f
	
	return PieChartHelper.generateDataSetPositive(
		data = listOf(
			Pair(primerTimeSpent, stringResource(R.string.field_primer)),
			Pair(baseTimeSpent, stringResource(R.string.field_base)),
			Pair(clearTimeSpent, stringResource(R.string.field_clear)),
		),
		colors = listOf(
			Grade.A.rgb,
			Grade.B.rgb,
			Grade.C.rgb,
		)
	)
}
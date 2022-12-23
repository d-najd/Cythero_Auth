package com.tradiebot.cythero.presentation.analytics.components.reports.usage.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUsageSortable
import com.tradiebot.cythero.util.CytheroDateFormat

@Composable
fun AnalyticsUsageReportCard(
	analytic: AnalyticsUsageSortable,
	width: Dp,
	onShowUsageItemInfo: (AnalyticsUsageSortable) -> Unit,
) {
	Card(
		modifier = Modifier
			.padding(vertical = 2.dp)
			.height(50.dp)
			.clickable { onShowUsageItemInfo(analytic) },
		shape = RoundedCornerShape(6.dp),
	) {
		Row(
			modifier = Modifier
				.padding(vertical = 8.dp, horizontal = 8.dp)
				.fillMaxHeight()
				.background(MaterialTheme.colorScheme.surfaceVariant),
			verticalAlignment = Alignment.CenterVertically,
		) {
			AnalyticsUsageBoxText(
				modifier = Modifier.width(width) ,
				text = analytic.user,
			)
			AnalyticsUsageBoxText(
				modifier = Modifier.width(width) ,
				text = stringResource(analytic.part.nameId),
			)
			AnalyticsUsageBoxText(
				modifier = Modifier.width(width) ,
				text = CytheroDateFormat.defaultRequestDateFormat().format(analytic.date),
			)
			AnalyticsUsageBoxText(
				modifier = Modifier.width(width) ,
				text = analytic.paintUsedMl.toString(),
			)
			AnalyticsUsageBoxText(
				modifier = Modifier.width(width) ,
				text = analytic.totalTimeSpentMin.toString(),
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

package com.tradiebot.cythero.presentation.analytics.components.reports.usage.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.NavigateBefore
import androidx.compose.material.icons.rounded.NavigateNext
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUsageSortableHolder
import kotlin.math.roundToInt

@OptIn(ExperimentalUnitApi::class)
@Composable
fun AnalyticsUsageChangeScreenContent(
	analytics: AnalyticsUsageSortableHolder,
	screenIndex: Int,
	onChangeScreenIndex: (Int) -> Unit
) {
	Row(
		modifier = Modifier
			.fillMaxWidth(),
		horizontalArrangement = Arrangement.Center,
		verticalAlignment = Alignment.CenterVertically,
	) {
		IconButton(
			onClick = {
				if(screenIndex > 1){
					onChangeScreenIndex(screenIndex - 1)
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
					onChangeScreenIndex(screenIndex + 1)
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
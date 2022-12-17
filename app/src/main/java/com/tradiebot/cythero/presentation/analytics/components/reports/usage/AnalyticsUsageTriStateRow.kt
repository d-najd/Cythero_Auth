package com.tradiebot.cythero.presentation.analytics.components.reports.usage

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDownward
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.material.icons.rounded.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUsageSortType
import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUsageSortableHolder
import com.tradiebot.cythero.presentation.components.CytheroCard

@Composable
fun AnalyticsUsageTriStateRow(
	modifier: Modifier,
	state: ToggleableState,
	text: String,
	onClick: (ToggleableState) -> Unit,
	iconButtonHeight: Float = 24f,
	onStateIcon: ImageVector = Icons.Rounded.ArrowUpward,
	offStateIcon: ImageVector = Icons.Rounded.ArrowDownward,
	indeterminateStateIcon: ImageVector = Icons.Rounded.FilterList,
){
	Row(
		modifier = modifier,
		verticalAlignment = Alignment.CenterVertically
	) {
		Column(
			verticalArrangement = Arrangement.Center,
			modifier = Modifier
				.fillMaxHeight(),
		) {
			Text(
				maxLines = 2,
				color = MaterialTheme.colorScheme.onSurfaceVariant,
				textAlign = TextAlign.Center,
				text = text,
			)
		}
		
		Column(
			verticalArrangement = Arrangement.Center,
			modifier = Modifier.fillMaxSize()
		) {
			IconButton(
				onClick = { onClick(state) },
				colors = IconButtonDefaults.iconButtonColors(
					contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
				),
				modifier = Modifier
					.align(Alignment.End)
					.height(iconButtonHeight.dp),
			) {
				Icon(
					imageVector = when (state) {
						ToggleableState.On -> onStateIcon
						ToggleableState.Off -> offStateIcon
						ToggleableState.Indeterminate -> indeterminateStateIcon
					},
					contentDescription = ""
				)
			}
		}
	}
}

/**
 * helper used to determine the analyticsUsageState
 * @return [ToggleableState.On] if [AnalyticsUsageSortableHolder.sortType] matches [type] and [AnalyticsUsageSortableHolder.reverse] is false,
 * [ToggleableState.Off] if [AnalyticsUsageSortableHolder.sortType] matches [type] and [AnalyticsUsageSortableHolder.reverse] is true,
 * [ToggleableState.Indeterminate] if [AnalyticsUsageSortableHolder.sortType] doesn't match [type] regardless of [AnalyticsUsageSortableHolder.reverse]
 */
fun analyticsUsageToggleableStateHelper(
	analytics: AnalyticsUsageSortableHolder,
	type: AnalyticsUsageSortType,
): ToggleableState = if(analytics.sortType == type && analytics.reverse) {
	ToggleableState.On
} else if (analytics.sortType == type) {
	ToggleableState.Off
} else {
	ToggleableState.Indeterminate
}

@Composable
@Preview(
	widthDp = 250,
	heightDp = 100,
)
private fun AnalyticsUsageTriStateRowPreview(){
	var state by remember { mutableStateOf(ToggleableState.Indeterminate) }
	
	CytheroCard {
		AnalyticsUsageTriStateRow(
			modifier = Modifier.width(200.dp),
			state = state,
			text = "Example",
			onClick = { prevState ->
				state = if(prevState == ToggleableState.On){
					ToggleableState.Off
				} else {
					ToggleableState.On
				}
			}
		)
	}
}

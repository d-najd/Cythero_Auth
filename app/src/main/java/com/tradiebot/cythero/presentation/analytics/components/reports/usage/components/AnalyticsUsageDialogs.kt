package com.tradiebot.cythero.presentation.analytics.components.reports.usage.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.tradiebot.cythero.R

@Composable
fun AnalyticsUsageItemInfoDialog(
	onDismissRequest: () -> Unit,
) {
	AlertDialog(
		onDismissRequest = onDismissRequest,
		confirmButton = { },
		dismissButton = { },
		title = {
			Text(text = stringResource(R.string.field_session_info))
		},
		text = {
			Text(text = "he")
		}
	)
}

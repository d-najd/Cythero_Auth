package com.tradiebot.cythero.presentation.analytics.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalUnitApi::class)
@Composable
fun AnalyticsNoContentText(
	text: String,
){
	Text(
		modifier = Modifier
			.padding(vertical = 16.dp, horizontal = 22.dp)
			.fillMaxWidth(),
		//color = MaterialTheme.colorScheme.onSurfaceVariant,
		lineHeight = TextUnit(22f, TextUnitType.Sp),
		fontSize = TextUnit(19f, TextUnitType.Sp),
		fontWeight = FontWeight.Bold,
		textAlign = TextAlign.Center,
		text = text
	)
}
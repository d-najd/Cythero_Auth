package com.tradiebot.cythero.presentation.analytics.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*

@Composable
fun AnalyticsNoContentText(
	text: String,
){
	Text(
		modifier = Modifier
			.padding(vertical = 16.dp, horizontal = 22.dp)
			.fillMaxWidth(),
		//color = MaterialTheme.colorScheme.onSurfaceVariant,
		lineHeight = 22.sp,
		fontSize = 19.sp,
		fontWeight = FontWeight.Bold,
		textAlign = TextAlign.Center,
		text = text
	)
}
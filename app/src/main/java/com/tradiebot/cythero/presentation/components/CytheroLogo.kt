package com.tradiebot.cythero.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CytheroLogo(
	modifier: Modifier = Modifier,
) {
	Row(
		modifier = modifier
			.fillMaxWidth()
			.padding(
				start = 12.dp,
				end = 12.dp,
				top = 22.dp,
				bottom = 16.dp
			),
		horizontalArrangement = Arrangement.Center,
		verticalAlignment = Alignment.CenterVertically
	) {
		Text(
			text = "C",
			fontSize = 32.sp,
			color = MaterialTheme.colorScheme.primary,
			fontWeight = FontWeight.SemiBold,
		)
		Text(
			text = "ythero",
			color = Color.White.copy(.8f),
			fontSize = 28.sp,
			fontWeight = FontWeight.SemiBold,
		)
		
	}
}
package com.tradiebot.cythero.presentation.components.dialogs

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object CytheroTextFieldDefaults {
	@Composable
	@OptIn(ExperimentalMaterial3Api::class)
	fun outlinedTextFieldBrightColors(): TextFieldColors {
		return TextFieldDefaults.outlinedTextFieldColors(
			textColor = Color.White.copy(.8f),
			unfocusedLabelColor = Color.White.copy(.7f),
			unfocusedSupportingTextColor = Color.White.copy(.5f),
			focusedTrailingIconColor = Color.White.copy(.8f),
			unfocusedTrailingIconColor = Color.White.copy(.8f),
			placeholderColor = Color.White.copy(.7f),
		)
	}
}
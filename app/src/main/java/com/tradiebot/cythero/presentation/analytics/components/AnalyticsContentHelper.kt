package com.tradiebot.cythero.presentation.analytics.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.pluralStringResource
import com.tradiebot.cythero.R
import com.tradiebot.cythero.util.conditionalIncludeDecimals
import kotlin.math.roundToInt

object AnalyticsContentHelper {
	
	@Composable
	@OptIn(ExperimentalComposeUiApi::class)
	fun generateStringFromTimePlayed(timePlayedSec: Long): String{
		return when (timePlayedSec){
			in 3600 .. Long.MAX_VALUE -> {
				val hours = timePlayedSec/3600f
				"${hours.conditionalIncludeDecimals(condition = hours < 10)} " +
					pluralStringResource(id = R.plurals.hours, count = hours.roundToInt())
			}
			in 60..3599 -> {
				val minutes = timePlayedSec/60f
				"${minutes.conditionalIncludeDecimals(condition = minutes < 10)} " +
					pluralStringResource(id = R.plurals.minutes, count = (minutes).roundToInt())
			}
			else -> {
				"$timePlayedSec " +
					pluralStringResource(id = R.plurals.seconds, count = timePlayedSec.toInt())
			}
		}
	}
}

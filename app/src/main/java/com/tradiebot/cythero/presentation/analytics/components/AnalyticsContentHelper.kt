package com.tradiebot.cythero.presentation.analytics.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.pluralStringResource
import com.tradiebot.cythero.R
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

object AnalyticsContentHelper {
	
	@Composable
	@OptIn(ExperimentalComposeUiApi::class)
	fun generateStringFromTimePlayed(timePlayedSec: Long): String{
		return if (timePlayedSec >= 3600){
			"${shouldIncludeDecimals(timePlayedSec/3600f)} ${pluralStringResource(id = R.plurals.hours, count = (timePlayedSec/3600).toInt())}"
		} else if (timePlayedSec >= 60) {
			"${shouldIncludeDecimals(timePlayedSec/60f)} ${pluralStringResource(id = R.plurals.minutes, count = (timePlayedSec/60).toInt())}"
		} else {
			"$timePlayedSec ${pluralStringResource(id = R.plurals.seconds, count = timePlayedSec.toInt())}"
		}
	}
	
	/**
	 * decides if it is necessary to include decimals for a given value, if the given value is
	 * { value < 10 } then 1 decimal will be included, if not no decimals will be included
	 *
	 * @param value input
	 * @return string value with 1 decimal if { value < 10 } or no decimals if bigger
	 * @see [shouldIncludeDecimals]
	 */
	private fun shouldIncludeDecimals(value: Float): String {
		return if (value < 10){
			val df = includeDecimals(1)
			df.roundingMode = RoundingMode.HALF_UP
			df.format(value)
		} else value.roundToInt().toString()
	}
	
	/**
	 * decides if it is necessary to include decimals for a given value, if the given value is
	 * { value < 10 } then 1 decimal will be included, if not no decimals will be included
	 *
	 * @param value input
	 * @return string value with 1 decimal if { value < 10 } or no decimals if bigger
	 * @see [shouldIncludeDecimals]
	 */
	fun shouldIncludeDecimals(value: Double): String {
		return if (value < 10){
			val df = includeDecimals(1)
			df.roundingMode = RoundingMode.HALF_UP
			df.format(value)
		} else value.roundToInt().toString()
	}
	
	private fun includeDecimals(decimals: Int): DecimalFormat {
		if(decimals < 1) throw IllegalArgumentException("1 or more decimal spaces are required")
		val pattern = "#." + "#".repeat(decimals)
		return DecimalFormat(pattern)
	}
}

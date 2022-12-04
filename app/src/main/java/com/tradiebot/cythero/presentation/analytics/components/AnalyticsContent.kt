package com.tradiebot.cythero.presentation.analytics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.presentation.analytics.components.subcards.*
import com.tradiebot.cythero.util.convertPixelsToDp
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

@Composable
fun AnalyticsContent(
    state: AnalyticsScreenState.Success,
    contentPadding: PaddingValues,
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .offset(y = LocalContext.current.convertPixelsToDp(scrollState.value.toFloat()).dp * -1)
            .fillMaxWidth()
            .height(175.dp)
            .background(MaterialTheme.colorScheme.primary)
    )

    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .padding(contentPadding)
            //.padding(vertical = 24.dp)
            .verticalScroll(scrollState)
            .fillMaxSize(),
    ) {

        AnalyticsGetAnalyticsCard(
            state = state,
        )

        AnalyticsGeneralCard(
            state = state,
        )

        AnalyticsGradeCard(
            state = state,
        )

        AnalyticsLatestSessionCard(
            state = state,
        )

        AnalyticsCoverageGraph(
            state = state
        )

        Divider(
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 12.dp)
        )

        AnalyticsBasicFields(
            state = state,
        )
    }
}

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
     */
    fun shouldIncludeDecimals(value: Float): String {
        return if (value < 10){
            val df = DecimalFormat("#.#")
            df.roundingMode = RoundingMode.HALF_UP
            df.format(value)
        } else value.roundToInt().toString()
    }
}

package com.tradiebot.cythero.presentation.analytics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreen
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsReportTypeScreenState
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsUserReportScreenState
import com.tradiebot.cythero.presentation.analytics.components.reports.user.AnalyticsUserReportContent
import com.tradiebot.cythero.presentation.analytics.components.reports.user.cards.*
import com.tradiebot.cythero.presentation.components.LoadingScreen
import com.tradiebot.cythero.util.convertPixelsToDp
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.Date
import kotlin.math.roundToInt

@Composable
fun AnalyticsContent(
    reportTypeState: AnalyticsReportTypeScreenState.Success,
    userReportState: AnalyticsUserReportScreenState,
    contentPadding: PaddingValues,

    //Report Type Content
    onGenerateUserReportClicked: (AnalyticsScreen.SelectedReportType, Pair<Date, Date>) -> Unit,
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
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            //.padding(vertical = 24.dp)
            .verticalScroll(scrollState)
            .fillMaxSize(),
    ) {

        AnalyticsGetAnalyticsCard(
            state = reportTypeState,
            onGenerateUserReportClicked = onGenerateUserReportClicked,
        )

        if (userReportState is AnalyticsUserReportScreenState.Success) {
            AnalyticsUserReportContent(
                state = userReportState,
                contentPadding = contentPadding,
            )
        } else if (userReportState is AnalyticsUserReportScreenState.Loading) {
            LoadingScreen()
        }
    }

}

object AnalyticsContentHelper {
    // TODO round milliliters to liters as well

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
    fun shouldIncludeDecimals(value: Float): String {
        return if (value < 10){
            val df = DecimalFormat("#.#")
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
            val df = DecimalFormat("#.#")
            df.roundingMode = RoundingMode.HALF_UP
            df.format(value)
        } else value.roundToInt().toString()
    }
}

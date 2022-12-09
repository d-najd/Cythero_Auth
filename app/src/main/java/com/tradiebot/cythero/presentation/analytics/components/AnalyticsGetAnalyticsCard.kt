package com.tradiebot.cythero.presentation.analytics.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsType
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsReportTypeScreenState
import com.tradiebot.cythero.domain.analytics.PartEnum
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.components.CytheroDropdownMenu
import com.tradiebot.cythero.presentation.components.CytheroMultipurposeMenu
import com.tradiebot.cythero.presentation.components.dialogs.DateRangePickerDialog
import com.tradiebot.cythero.presentation.util.show
import com.tradiebot.cythero.util.CytheroDateFormat
import com.tradiebot.cythero.util.toEndOfDay
import com.tradiebot.cythero.util.toStartOfDay
import java.util.*
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun AnalyticsGetAnalyticsCard(
    state: AnalyticsReportTypeScreenState.Success,
    onGenerateUserReportClicked: (Pair<Date, Date>) -> Unit,
    onGeneratePartReportClicked: (PartEnum) -> Unit,
) {
    var selectedReportType by remember { mutableStateOf(AnalyticsType.USER) }

    var dateRange by remember { mutableStateOf(Pair(
        first = Date(Date().time - Duration.Companion.convert(
            168.0, DurationUnit.HOURS, DurationUnit.MILLISECONDS).toLong()),
        second = Date()
    )) }

    var selectedPartType by remember { mutableStateOf(PartEnum.FENDER) }

    CytheroCard(
        title = stringResource(R.string.field_analytics),
        modifier = Modifier
            .padding(top = 24.dp)
    ) {
        ReportType(
            selectedReportType = selectedReportType,
            onChangeReportType = { selectedReportType = it },
        )

        when(selectedReportType) {
            AnalyticsType.USER,
            AnalyticsType.USAGE -> {
                SelectDateRange(
                    dateRange = dateRange,
                    onChangeDateRange = { dateRange = it }
                )
            }

            AnalyticsType.PART -> {
                SelectPartType(
                    part = selectedPartType,
                    onChangePart = { selectedPartType = it }
                )
            }
        }

        Button(
            onClick = {
                when(selectedReportType){
                    AnalyticsType.USER -> onGenerateUserReportClicked(dateRange)
                    AnalyticsType.PART -> onGeneratePartReportClicked(selectedPartType)
                    AnalyticsType.USAGE -> {

                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .padding(top = 12.dp),
        ) {
            Text(text = stringResource(R.string.action_generate_report))
        }
    }
}

@Composable fun SelectPartType(
    part: PartEnum,
    onChangePart: (PartEnum) -> Unit,
) {

}

@Composable
private fun ReportType(
    selectedReportType: AnalyticsType,
    onChangeReportType: (AnalyticsType) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    CytheroDropdownMenu(
        title = stringResource(R.string.info_select_report_type),
        text = stringResource(selectedReportType.reportTypeId),
        expanded = expanded,
        onDismissRequest = { expanded = false },
        onClick = { expanded = !expanded },
    ) {
        DropdownMenuItem(
            text = { Text(text = stringResource(AnalyticsType.USER.reportTypeId)) },
            onClick = {
                onChangeReportType(AnalyticsType.USER)
                expanded = false
            }
        )
        DropdownMenuItem(
            text = { Text(text = stringResource(AnalyticsType.PART.reportTypeId)) },
            onClick = {
                onChangeReportType(AnalyticsType.PART)
                expanded = false
            }
        )
        DropdownMenuItem(
            text = { Text(text = stringResource(AnalyticsType.USAGE.reportTypeId)) },
            onClick = {
                onChangeReportType(AnalyticsType.USAGE)
                expanded = false
            }
        )
    }
}

@Composable
private fun SelectDateRange(
    dateRange: Pair<Date, Date>,
    onChangeDateRange: (Pair<Date, Date>) -> Unit,
) {
    val dateFormat = CytheroDateFormat.defaultDateFormat()

    val dateRangePicker = DateRangePickerDialog(
        select = Pair(
            first = dateRange.first,
            second = dateRange.second
        ),
        title = R.string.info_select_date_range,
        onDateSelected = { f, s -> onChangeDateRange(Pair(f.toStartOfDay(), s.toEndOfDay())) }
    )

    CytheroMultipurposeMenu(
        modifier = Modifier
            .padding(top = 20.dp),
        title = stringResource(R.string.info_select_date_range),
        text = "${dateFormat.format(dateRange.first)} - ${dateFormat.format(dateRange.second)}",
        onClick = {
            dateRangePicker.show()
        }
    )
}
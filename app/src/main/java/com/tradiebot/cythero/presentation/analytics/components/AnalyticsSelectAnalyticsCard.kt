package com.tradiebot.cythero.presentation.analytics.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.domain.analytics.Part
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.components.CytheroDropdownMenu
import com.tradiebot.cythero.presentation.components.CytheroMultipurposeMenu
import com.tradiebot.cythero.presentation.components.dialogs.CytheroButtonDefaults
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
fun AnalyticsSelectAnalyticsCard(
    onGenerateUserReportClicked: (Pair<Date, Date>) -> Unit,
    onGeneratePartReportClicked: (Part) -> Unit,
    onGenerateUsageReportClicked: (Pair<Date, Date>) -> Unit,
) {
    var selectedReportType by remember { mutableStateOf(AnalyticsType.USER) }

    var dateRange by remember { mutableStateOf(Pair(
        first = Date(Date().time - Duration.Companion.convert(
            168.0, DurationUnit.HOURS, DurationUnit.MILLISECONDS).toLong()),
        second = Date()
    )) }
    var selectedPartType by remember { mutableStateOf(Part.FENDER) }

    CytheroCard(
        title = stringResource(R.string.field_analytics),
        modifier = Modifier
            .padding(top = 24.dp),
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
                    selectedPart = selectedPartType,
                    onChangeSelectedPart = { selectedPartType = it }
                )
            }
        }

        Button(
            shape = RoundedCornerShape(CytheroButtonDefaults.BUTTON_CORNER_ROUNDING),
            onClick = {
                when(selectedReportType){
                    AnalyticsType.USER -> onGenerateUserReportClicked(dateRange)
                    AnalyticsType.PART -> onGeneratePartReportClicked(selectedPartType)
                    AnalyticsType.USAGE -> onGenerateUsageReportClicked(dateRange)
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

@Composable
private fun SelectPartType(
    selectedPart: Part,
    onChangeSelectedPart: (Part) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val parts = Part.values()

    CytheroDropdownMenu(
        title = stringResource(R.string.info_select_report_type),
        text = stringResource(selectedPart.nameId),
        expanded = expanded,
        onDismissRequest = { expanded = false },
        onClick = { expanded = !expanded },
    ) {
        for(part in parts){
            DropdownMenuItem(
                text = { Text(text = stringResource(part.nameId)) },
                onClick = {
                    onChangeSelectedPart(part)
                    expanded = false
                }
            )
        }
    }
}

@Composable
private fun ReportType(
    selectedReportType: AnalyticsType,
    onChangeReportType: (AnalyticsType) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val reports = AnalyticsType.values()

    CytheroDropdownMenu(
        modifier = Modifier.padding(bottom = 20.dp),
        title = stringResource(R.string.info_select_report_type),
        text = stringResource(selectedReportType.nameId),
        expanded = expanded,
        onDismissRequest = { expanded = false },
        onClick = { expanded = !expanded },
    ) {
        for(report in reports) {
            DropdownMenuItem(
                text = { Text(text = stringResource(report.nameId)) },
                onClick = {
                    onChangeReportType(report)
                    expanded = false
                }
            )
        }
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
        title = stringResource(R.string.info_select_date_range),
        text = "${dateFormat.format(dateRange.first)} - ${dateFormat.format(dateRange.second)}",
        onClick = {
            dateRangePicker.show()
        }
    )
}

private enum class AnalyticsType(val nameId: Int) {
    USER(R.string.action_select_user_report),
    PART(R.string.action_select_part),
    USAGE(R.string.action_select_usage),
}

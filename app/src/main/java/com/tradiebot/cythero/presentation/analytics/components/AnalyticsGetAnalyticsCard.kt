package com.tradiebot.cythero.presentation.analytics.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreen
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsReportTypeScreenState
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.components.CytheroDropdownMenu
import com.tradiebot.cythero.presentation.components.CytheroMultipurposeMenu
import com.tradiebot.cythero.presentation.components.dialogs.DateRangePickerDialog
import com.tradiebot.cythero.presentation.util.show
import com.tradiebot.cythero.util.CytheroDateFormat
import java.util.*
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime

@Composable
fun AnalyticsGetAnalyticsCard(
    state: AnalyticsReportTypeScreenState.Success,
    onGenerateReportClicked: (AnalyticsScreen.SelectedReportType) -> Unit,
) {
    var selectedReportType by remember { mutableStateOf(AnalyticsScreen.SelectedReportType.USER) }

    CytheroCard(
        title = stringResource(R.string.field_analytics),
        modifier = Modifier
            .padding(top = 24.dp)
    ) {
        ReportType(
            selectedReportType = selectedReportType,
            onChangeReportType = { selectedReportType = it },
        )

        SelectDateRange()

        Button(
            onClick = { onGenerateReportClicked(selectedReportType) },
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
private fun ReportType(
    selectedReportType: AnalyticsScreen.SelectedReportType,
    onChangeReportType: (AnalyticsScreen.SelectedReportType) -> Unit,
){
    var expanded by remember { mutableStateOf(false) }

    CytheroDropdownMenu(
        title = stringResource(R.string.info_select_report_type),
        text = stringResource(selectedReportType.reportTypeId),
        expanded = expanded,
        onDismissRequest = { expanded = false },
        onClick = { expanded = !expanded },
    ) {
        DropdownMenuItem(
            text = { Text(text = stringResource(AnalyticsScreen.SelectedReportType.USER.reportTypeId)) },
            onClick = {
                onChangeReportType(AnalyticsScreen.SelectedReportType.USER)
                expanded = false
            }
        )
        DropdownMenuItem(
            text = { Text(text = stringResource(AnalyticsScreen.SelectedReportType.PART.reportTypeId)) },
            onClick = {
                onChangeReportType(AnalyticsScreen.SelectedReportType.PART)
                expanded = false
            }
        )
        DropdownMenuItem(
            text = { Text(text = stringResource(AnalyticsScreen.SelectedReportType.USAGE.reportTypeId)) },
            onClick = {
                onChangeReportType(AnalyticsScreen.SelectedReportType.USAGE)
                expanded = false
            }
        )
    }
}

@OptIn(ExperimentalTime::class)
@Composable
private fun SelectDateRange(){
    val dateFormat = CytheroDateFormat.defaultDateFormat()
    var date by remember { mutableStateOf(Pair(
        first = TextFieldValue(dateFormat.format(Date(
            Date().time -
                    Duration.Companion.convert(168.0, DurationUnit.HOURS, DurationUnit.MILLISECONDS).toLong()
        ))),
        second = TextFieldValue(dateFormat.format(Date()))
    )) }

    val dateRangePicker = DateRangePickerDialog(
        select = Pair(
            first = dateFormat.parse(date.first.text),
            second = dateFormat.parse(date.second.text)
        ),
        title = R.string.info_select_date_range,
        onDateSelected = { f, s ->
            date = Pair(
                first = TextFieldValue(dateFormat.format(f)),
                second = TextFieldValue(dateFormat.format(s)),
            )
        }
    )

    CytheroMultipurposeMenu(
        modifier = Modifier
            .padding(top = 20.dp),
        title = stringResource(R.string.info_select_date_range),
        text = "${date.first.text} - ${date.second.text}",
        onClick = {
            dateRangePicker.show()
        }
    )
}
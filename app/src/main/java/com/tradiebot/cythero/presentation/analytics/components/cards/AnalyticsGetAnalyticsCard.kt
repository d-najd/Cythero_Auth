package com.tradiebot.cythero.presentation.analytics.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.components.CytheroDropdownMenu
import com.tradiebot.cythero.presentation.components.CytheroMultipurposeMenu
import com.tradiebot.cythero.presentation.components.dialogs.DateRangePickerDialog
import com.tradiebot.cythero.presentation.util.show
import com.tradiebot.cythero.util.CytheroDateFormat
import java.util.*

@Composable
fun AnalyticsGetAnalyticsCard(
    state: AnalyticsScreenState.Success,
) {
    CytheroCard(
        title = stringResource(R.string.field_analytics),
        modifier = Modifier
            .padding(top = 24.dp)
    ) {
        ReportType()
        SelectDateRange()
    }
}

@Composable
private fun ReportType(){
    var expanded by remember { mutableStateOf(false) }
    var selectedReportType by remember { mutableStateOf(SelectedReportType.USER) }

    CytheroDropdownMenu(
        title = stringResource(R.string.info_select_report_type),
        text = stringResource(selectedReportType.reportTypeId),
        expanded = expanded,
        onDismissRequest = { expanded = false },
        onClick = { expanded = !expanded },
    ) {
        DropdownMenuItem(
            text = { Text(text = stringResource(SelectedReportType.USER.reportTypeId)) },
            onClick = {
                selectedReportType = SelectedReportType.USER
                expanded = false
            }
        )
        DropdownMenuItem(
            text = { Text(text = stringResource(SelectedReportType.PART.reportTypeId)) },
            onClick = {
                selectedReportType = SelectedReportType.PART
                expanded = false
            }
        )
        DropdownMenuItem(
            text = { Text(text = stringResource(SelectedReportType.USAGE.reportTypeId)) },
            onClick = {
                selectedReportType = SelectedReportType.USAGE
                expanded = false
            }
        )
    }
}

@Composable
private fun SelectDateRange(){
    val dateFormat = CytheroDateFormat.defaultDateFormat()
    var date by remember { mutableStateOf(Pair(
        first = TextFieldValue(dateFormat.format(Date())),
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
            .padding(top = 20.dp, bottom = 16.dp),
        title = stringResource(R.string.info_select_date_range),
        text = "${date.first.text} - ${date.second.text}",
        onClick = {
            dateRangePicker.show()
        }
    )
}

// TODO this will probably have to be moved to AnalyticsScreenState
private enum class SelectedReportType(val reportTypeId: Int) {
    USER(R.string.action_select_user_report),
    PART(R.string.action_select_part),
    USAGE(R.string.action_select_usage),
}
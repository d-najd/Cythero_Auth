package com.tradiebot.cythero.presentation.analytics.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.android.material.datepicker.MaterialDatePicker
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.components.CytheroDatePicker
import com.tradiebot.cythero.presentation.components.CytheroDropdownMenu
import java.text.SimpleDateFormat

@Composable
fun AnalyticsGetAnalyticsCard(
    state: AnalyticsScreenState.Success,
) {
    CytheroCard(
        title = stringResource(R.string.field_analytics),
        modifier = Modifier
            .padding(top = 24.dp)
            .height(250.dp)
    ) {
        var expanded by remember { mutableStateOf(false) }
        var selectedReportType by remember { mutableStateOf(SelectedReportType.USER) }
        // var selectDateRange by remember { mutableStateOf(SimpleDateFormat()) }

        CytheroDatePicker()

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
}

// TODO this will probably have to be moved to AnalyticsScreenState
private enum class SelectedReportType(val reportTypeId: Int) {
    USER(R.string.action_select_user_report),
    PART(R.string.action_select_part),
    USAGE(R.string.action_select_usage),
}
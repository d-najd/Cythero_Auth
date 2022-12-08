package com.tradiebot.cythero.presentation.analytics.components.reports.user.cards

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsUserReportScreenState
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.components.charts.PieChart
import com.tradiebot.cythero.presentation.components.charts.PieChartHelper

@Composable
fun AnalyticsGradeCard(
    state: AnalyticsUserReportScreenState.Success,
){
    val analyticsTable = state.userAnalytics[state.auth.user.id]!!.userAnalyticsTable
    val grades = analyticsTable.grade.groupingBy { it }.eachCount().toSortedMap()
    val pieDataSet = PieChartHelper.generateDataFromGrades(grades)
    
    CytheroCard(
        title = stringResource(R.string.field_grades_breakdown),
        modifier = Modifier.height(275.dp),
    ) {
        PieChart(dataSet = pieDataSet)
    }
}
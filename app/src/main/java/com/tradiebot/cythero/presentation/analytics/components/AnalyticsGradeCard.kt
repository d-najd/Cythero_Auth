package com.tradiebot.cythero.presentation.analytics.components

import androidx.compose.runtime.Composable
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.presentation.components.charts.PieChartComponent
import com.tradiebot.cythero.presentation.components.charts.PieChartHelper

@Composable
fun AnalyticsGradeCard(
    state: AnalyticsScreenState.Success,
){
    val analyticsTable = state.userAnalytics[state.auth.user.id]!!.analyticsTable

    val grades = analyticsTable.grade.groupingBy { it }.eachCount().toSortedMap()

    val pieDataSet = PieChartHelper.generateDataFromGrades(grades)
    PieChartComponent(pieDataSet)

}

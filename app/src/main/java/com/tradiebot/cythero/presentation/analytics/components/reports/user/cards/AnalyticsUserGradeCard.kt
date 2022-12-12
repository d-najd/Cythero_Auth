package com.tradiebot.cythero.presentation.analytics.components.reports.user.cards

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.data.PieDataSet
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.components.charts.PieChart
import com.tradiebot.cythero.presentation.components.charts.PieChartHelper
import kotlinx.coroutines.flow.*

@Composable
fun AnalyticsGradeCard(
    state: AnalyticsScreenState.UserSuccess,
){
    val analyticsTable = state.analytics[state.auth.user.id]!!.analyticsUserTable
    val grades = analyticsTable.grade.groupingBy { it }.eachCount().toSortedMap()

    val pieDataSet: Flow<PieDataSet> = flow { emit(PieChartHelper.dataFromGrades(grades)) }

    CytheroCard(
        title = stringResource(R.string.field_grades_breakdown),
        modifier = Modifier.height(275.dp),
    ) {
        PieChart(dataSet = pieDataSet)
    }
}

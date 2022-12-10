package com.tradiebot.cythero.presentation.analytics.components.reports.user.cards

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.data.PieDataSet
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsUserScreenState
import com.tradiebot.cythero.app.ui.login.LoginEvent
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.components.charts.PieChart
import com.tradiebot.cythero.presentation.components.charts.PieChartHelper
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

@Composable
fun AnalyticsGradeCard(
    state: AnalyticsUserScreenState.Success,
){
    val analyticsTable = state.analytics[state.auth.user.id]!!.analyticsUserTable
    val grades = analyticsTable.grade.groupingBy { it }.eachCount().toSortedMap()

    val pieDataSet: Flow<PieDataSet> = flow {
        emit(PieChartHelper.dataFromGrades(grades))
    }

    CytheroCard(
        title = stringResource(R.string.field_grades_breakdown),
        modifier = Modifier.height(275.dp),
    ) {
        PieChart(dataSet = pieDataSet)
    }
}
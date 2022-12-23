package com.tradiebot.cythero.presentation.analytics.components.reports.part.cards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.data.PieDataSet
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.domain.analytics.CoverageType
import com.tradiebot.cythero.domain.analytics.part.model.AnalyticsPart
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsPairField
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.components.chart.PieChart
import com.tradiebot.cythero.presentation.components.chart.PieChartHelper
import com.tradiebot.cythero.presentation.components.dialogs.CytheroButtonDefaults
import kotlinx.coroutines.flow.flow

@Composable
fun AnalyticsPartGradesBreakdownCard(
    state: AnalyticsScreenState.PartSuccess,
    onSelectedCoverageTypeChange: (CoverageType) -> Unit,
){
    val analytics = state.analytics[0]

    val timesPlayed = analytics.timesPlayed
    val averageGrade = when(state.selectedCoverageType) {
        CoverageType.OVERALL -> stringResource(analytics.averageGradeOverall.nameId)
        CoverageType.PRIMER -> stringResource(analytics.averageGradePrimer.nameId)
        CoverageType.BASE -> stringResource(analytics.averageGradeBase.nameId)
        CoverageType.CLEAR -> stringResource(analytics.averageGradeClear.nameId)
    }

    val gradePieDataSet = generateDataSet(
        analytics = analytics,
        selectedCoverageType = state.selectedCoverageType
    )

    CytheroCard(
        title = stringResource(R.string.field_grades_breakdown)
    ) {
        Column(
            modifier = Modifier
                .height(150.dp)
        ) {
            PieChart(dataSet = flow { emit(gradePieDataSet) })
        }

        Row(
            horizontalArrangement =Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val coverages = CoverageType.values()
            for (coverage in coverages){
                TextButton(
                    modifier = Modifier
                        .padding(horizontal = 4.dp),
                    shape = RoundedCornerShape(CytheroButtonDefaults.BUTTON_CORNER_ROUNDING),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if(state.selectedCoverageType == coverage)
                            MaterialTheme.colorScheme.primary else
                            Color.Transparent,
                        contentColor = if(state.selectedCoverageType == coverage)
                            MaterialTheme.colorScheme.onPrimary else
                            MaterialTheme.colorScheme.onBackground,
                    ),
                    onClick = { onSelectedCoverageTypeChange(coverage) }
                ) {
                    Text(
                        text = stringResource(coverage.nameId),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        AnalyticsPairField(stringResource(R.string.field_times_played), value = timesPlayed.toString())
        AnalyticsPairField(stringResource(R.string.field_average_grade), value = averageGrade)
    }
}

@Suppress("UselessCallOnCollection") // it is not useless 
@Composable
private fun generateDataSet(
    analytics: AnalyticsPart,
    selectedCoverageType: CoverageType,
): PieDataSet {
    val gradesOverall = PieChartHelper.dataFromGrades(analytics.overallGrade.filterNotNull().groupingBy { it }.eachCount().toSortedMap())
    val gradesPrimer = PieChartHelper.dataFromGrades(analytics.primerGrade.filterNotNull().groupingBy { it }.eachCount().toSortedMap())
    val gradesBase = PieChartHelper.dataFromGrades(analytics.baseGrade.filterNotNull().groupingBy { it }.eachCount().toSortedMap())
    val gradesClear = PieChartHelper.dataFromGrades(analytics.clearGrade.filterNotNull().groupingBy { it }.eachCount().toSortedMap())

    return when(selectedCoverageType) {
        CoverageType.OVERALL -> gradesOverall
        CoverageType.PRIMER -> gradesPrimer
        CoverageType.BASE -> gradesBase
        CoverageType.CLEAR -> gradesClear
    }
}

package com.tradiebot.cythero.presentation.analytics.components.reports.part.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsPartScreenState
import com.tradiebot.cythero.domain.analytics.CoverageType
import com.tradiebot.cythero.domain.analytics.part.model.AnalyticsPart
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsPairField
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.components.charts.PieChart
import com.tradiebot.cythero.presentation.components.charts.PieChartHelper
import com.tradiebot.cythero.presentation.components.dialogs.CytheroButtonDefaults
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun AnalyticsPartGradesBreakdownCard(
    state: AnalyticsPartScreenState.Success,
    selectedCoverageType: CoverageType,
    onSelectedCoverageTypeChange: (CoverageType) -> Unit,
){
    val analytics = state.analytics[0]

    val timesPlayed = analytics.timesPlayed
    val averageGrade = when(selectedCoverageType) {
        CoverageType.OVERALL -> stringResource(analytics.averageGradeOverall.nameId)
        CoverageType.PRIMER -> stringResource(analytics.averageGradePrimer.nameId)
        CoverageType.BASE -> stringResource(analytics.averageGradeBase.nameId)
        CoverageType.CLEAR -> stringResource(analytics.averageGradeClear.nameId)
    }

    val gradePieDataSet = mDataSet(
        analytics = analytics,
        selectedCoverageType = selectedCoverageType
    )

    CytheroCard(
        title = stringResource(R.string.field_grades_breakdown)
    ) {
        Column(
            modifier = Modifier
                .height(150.dp)
        ) {
            PieChart(dataSet = gradePieDataSet)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val coverages = CoverageType.values()
            for (coverage in coverages){
                TextButton(
                    shape = RoundedCornerShape(CytheroButtonDefaults.BUTTON_CORNER_ROUNDING),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if(selectedCoverageType == coverage)
                            MaterialTheme.colorScheme.primary else
                            Color.Transparent,
                        contentColor = if(selectedCoverageType == coverage)
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

@Composable
private fun mDataSet(
    analytics: AnalyticsPart,
    selectedCoverageType: CoverageType,
): Flow<PieDataSet> {
    val gradesOverall = PieChartHelper.dataFromGrades(analytics.overallGrade.groupingBy { it }.eachCount().toSortedMap())
    val gradesPrimer = PieChartHelper.dataFromGrades(analytics.primerGrade.groupingBy { it }.eachCount().toSortedMap())
    val gradesBase = PieChartHelper.dataFromGrades(analytics.baseGrade.groupingBy { it }.eachCount().toSortedMap())
    val gradesClear = PieChartHelper.dataFromGrades(analytics.clearGrade.groupingBy { it }.eachCount().toSortedMap())

    val gradePieDataSet: Flow<PieDataSet> = flow {
        when(selectedCoverageType) {
            CoverageType.OVERALL -> emit(gradesOverall)
            CoverageType.PRIMER -> emit(gradesPrimer)
            CoverageType.BASE -> emit(gradesBase)
            CoverageType.CLEAR -> emit(gradesClear)
        }
    }
    return gradePieDataSet
}

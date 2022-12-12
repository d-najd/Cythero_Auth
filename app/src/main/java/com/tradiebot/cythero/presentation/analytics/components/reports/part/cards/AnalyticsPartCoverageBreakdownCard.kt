package com.tradiebot.cythero.presentation.analytics.components.reports.part.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsPartScreenState
import com.tradiebot.cythero.domain.analytics.CoverageType
import com.tradiebot.cythero.domain.analytics.Grade
import com.tradiebot.cythero.domain.analytics.part.model.AnalyticsPart
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsPairField
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.components.charts.PieChart
import com.tradiebot.cythero.presentation.components.charts.PieChartHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.math.round

@Composable
fun AnalyticsPartCoverageBreakdownCard(
    state: AnalyticsPartScreenState.Success,
    selectedCoverageType: CoverageType,
) {
    val analytics = state.analytics[0]

    val coveragePrimer = coveragePrimerDataSet(analytics)
    val coverageBase = coverageBaseDataSet(analytics)
    val coverageClear = coverageClearDataSet(analytics)
    val coverageOverall = coverageOverallDataSet(analytics)

    var curDataSet by remember { mutableStateOf(coverageOverall) }

    curDataSet = when (selectedCoverageType) {
        CoverageType.OVERALL -> coverageOverall
        CoverageType.PRIMER -> coveragePrimer
        CoverageType.BASE -> coverageBase
        CoverageType.CLEAR -> coverageClear
    }

    val gradePieDataSet: Flow<PieDataSet> = flow {
        when(selectedCoverageType) { else -> emit(curDataSet) } // alternative to when with 1 field?
    }

    CytheroCard(
        title = stringResource(R.string.field_coverage_breakdown)
    ) {
        Column(
            modifier = Modifier
                .height(150.dp)
        ) {
            PieChart(dataSet = gradePieDataSet)

            when(selectedCoverageType) {
                CoverageType.OVERALL -> GenerateFieldsUnderChart(coverageOverall)
                CoverageType.PRIMER -> GenerateFieldsUnderChart(coveragePrimer)
                CoverageType.BASE -> GenerateFieldsUnderChart(coverageBase)
                CoverageType.CLEAR -> GenerateFieldsUnderChart(coverageClear)
            }
        }
    }
}

@Composable
private fun GenerateFieldsUnderChart(dataSet: PieDataSet){
    for(entry in dataSet.entries){
        AnalyticsPairField(key = entry.label, value = entry.value.toInt().toString())
    }
}

@Composable
private fun dataSetColorsList(): List<String> {
    return listOf(
        Grade.C.rgb,
        Grade.A.rgb,
        Grade.B.rgb
    )
}

@Composable
private fun coverageOverallDataSet(
    analytics: AnalyticsPart,
): PieDataSet {
    // Using the other coverage's entry sets will be error prone
    return PieChartHelper.dataFromEntriesAndColors(
        entries = listOf(
            PieEntry(
                round(
                    (analytics.averageHighCoverageBase + analytics.averageHighCoverageClear + analytics.averageHighCoveragePrimer)
                            / 3f
                ).toFloat(), stringResource(id = R.string.field_coverage_high)
            ),
            PieEntry(
                round(
                    (analytics.averageGoodCoverageBase + analytics.averageGoodCoverageClear + analytics.averageGoodCoveragePrimer)
                            / 3f
                ).toFloat(), stringResource(id = R.string.field_coverage_good)
            ),
            PieEntry(
                round(
                    (analytics.averageLowCoverageBase + analytics.averageLowCoverageClear + analytics.averageLowCoveragePrimer)
                            / 3f
                ).toFloat(), stringResource(id = R.string.field_coverage_low)
            )
        ),
        colors = dataSetColorsList()
    )
}

@Composable
private fun coverageClearDataSet(
    analytics: AnalyticsPart,
): PieDataSet {
    return PieChartHelper.dataFromEntriesAndColors(
        entries = listOf(
            PieEntry(
                round(analytics.averageHighCoverageClear).toFloat(),
                stringResource(R.string.field_coverage_high)
            ),
            PieEntry(
                round(analytics.averageGoodCoverageClear).toFloat(),
                stringResource(R.string.field_coverage_good)
            ),
            PieEntry(
                round(analytics.averageLowCoverageClear).toFloat(),
                stringResource(R.string.field_coverage_low)
            ),
        ),
        colors = dataSetColorsList()
    )
}

@Composable
private fun coverageBaseDataSet(
    analytics: AnalyticsPart,
): PieDataSet {
    return PieChartHelper.dataFromEntriesAndColors(
        entries = listOf(
            PieEntry(
                round(analytics.averageHighCoverageBase).toFloat(),
                stringResource(R.string.field_coverage_high)
            ),
            PieEntry(
                round(analytics.averageGoodCoverageBase).toFloat(),
                stringResource(R.string.field_coverage_good)
            ),
            PieEntry(
                round(analytics.averageLowCoverageBase).toFloat(),
                stringResource(R.string.field_coverage_low)
            ),
        ),
        colors = dataSetColorsList()
    )
}

@Composable
private fun coveragePrimerDataSet(
    analytics: AnalyticsPart,
): PieDataSet {
    return PieChartHelper.dataFromEntriesAndColors(
        entries = listOf(
            PieEntry(
                round(analytics.averageHighCoveragePrimer).toFloat(),
                stringResource(R.string.field_coverage_high)
            ),
            PieEntry(
                round(analytics.averageGoodCoveragePrimer).toFloat(),
                stringResource(R.string.field_coverage_good)
            ),
            PieEntry(
                round(analytics.averageLowCoveragePrimer).toFloat(),
                stringResource(R.string.field_coverage_low)
            ),
        ),
        colors = dataSetColorsList()
    )
}
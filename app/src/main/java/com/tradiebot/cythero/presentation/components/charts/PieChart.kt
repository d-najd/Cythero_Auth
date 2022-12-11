package com.tradiebot.cythero.presentation.components.charts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.tradiebot.cythero.app.util.view.ContextHolder
import com.tradiebot.cythero.domain.analytics.Grade
import com.tradiebot.cythero.presentation.util.ChartsHelper
import com.tradiebot.cythero.presentation.util.CytheroLegend
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import java.util.SortedMap

/**
 * Creates a PieChart which fills max size and gets centered, currently there is no way to align the
 * chart and doing so requires rework of some components in the library.
 *
 * @param dataSet data set for the chart
 * legend changes the offset on the chart
 *
 * TODO while hovering a field in chart display number of overall grades
 *  in that category like in the webapp also round just the tip of the slices and this needs a rework
 *  and this is not reusable at its current state
 */
@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    dataSet: Flow<PieDataSet>,
    offsets: Offset = Offset(PieChartHelper.PIE_CHART_OFFSET_LEFT, PieChartHelper.PIE_CHART_OFFSET_TOP),
    sliceSize: Float = 5f,

    legend: CytheroLegend = ChartsHelper.defaultPieCLegend(),
) {
    AndroidView(
        modifier = modifier
            .fillMaxSize(),
        factory = { context ->
            PieChart(context)
        },

        update = { pieChart ->
            pieChart.apply {
                setDrawEntryLabels(false)
                setUsePercentValues(false)
                setHoleColor(Color.Transparent.toArgb())
                setDrawMarkers(true)
                setTransparentCircleAlpha(0)
                extraTopOffset = offsets.y
                extraLeftOffset = offsets.x
                description.isEnabled = false

                ChartsHelper.copyLegendVars(
                    fLegend = this.legend,
                    sLegend = legend
                )

                runBlocking {
                    dataSet.collectLatest {
                        val lastDataSet = dataSet.last()
                        lastDataSet.setDrawValues(true)
                        lastDataSet.sliceSpace = sliceSize
                        data = PieData(lastDataSet)

                        invalidate()
                    }
                }
            }
        })
}

object PieChartHelper {
    fun dataFromGrades(grades: SortedMap<Grade, Int>): PieDataSet {
        val entries = mutableListOf<PieEntry>()
        val colors = mutableListOf<Int>()

        for (grade in grades) {
            entries.add(PieEntry(grade.value.toFloat(), "Grade ${Injekt.get<ContextHolder>().getString(grade.key.nameId)}"))
            colors.add(
                when (grade.key){
                    Grade.A -> android.graphics.Color.parseColor(
                        Grade.A.rgb)
                    Grade.B -> android.graphics.Color.parseColor(
                        Grade.B.rgb)
                    Grade.C -> android.graphics.Color.parseColor(
                        Grade.C.rgb)
                    else -> throw IllegalStateException("Invalid grade")
                }
            )
        }
        val dataSet = PieDataSet(entries, "")
        dataSet.colors = colors

        return dataSet
    }

    fun dataFromEntriesAndColors(
        entries: List<PieEntry>,
        colors: List<String>,
    ): PieDataSet {
        val mColors = mutableListOf<Int>()
        val mEntries = mutableListOf<PieEntry>()
        if(colors.size < entries.size) throw IllegalArgumentException()
        for(i in entries.indices){
            if(entries[i].value > 0){
                mEntries.add(entries[i])
                mColors.add(android.graphics.Color.parseColor(colors[i]))
            }
        }

        val dataSet = PieDataSet(mEntries, "")
        dataSet.colors = mColors

        return dataSet
    }

    /** default offset on the chart */
    const val PIE_CHART_OFFSET_TOP = 0f
    /** default offset on the chart */
    const val PIE_CHART_OFFSET_LEFT = -50f

    /** default offset on the legend */
    const val PIE_CHART_LEGEND_X_OFFSET = 12.5f
}
package com.tradiebot.cythero.presentation.components.charts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.tradiebot.cythero.presentation.util.ChartsHelper
import java.util.SortedMap

/**
 * Creates a PieChart which fills max size and gets centered, currently there is no way to align the
 * chart and doing so requires rework of some components in the library.
 *
 * @param dataSet data set for the chart
 * @param offsetLeft offset to the left on the chart in <b>DP</b. use negative values for offset to the right
 * @param offsetTop offset to the top on the chart in <b>DP</b. use negative values for offset to the bottom
 * @param isLegendEnabled whether the legend is enabled or not on by default. <b>NOTE</b> enabling the
 * legend changes the offset on the chart
 *
 * TODO while hovering a field in chart display number of overall grades
 *  in that category like in the webapp also round just the tip of the slices and this needs a rework
 *
 */
@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    dataSet: PieDataSet,
    offsetLeft: Float = PieChartHelper.PIE_CHART_OFFSET_LEFT,
    offsetTop: Float = PieChartHelper.PIE_CHART_OFFSET_TOP,
    isLegendEnabled: Boolean = true,
) {

    AndroidView(
        modifier = modifier
            .fillMaxSize(),
        factory = { context ->
            PieChart(context)
        },

        update = { pieChart ->
            pieChart.apply {
                dataSet.setDrawValues(false)
                dataSet.sliceSpace = 5f

                setDrawEntryLabels(false)
                setUsePercentValues(false)
                setHoleColor(Color.Transparent.toArgb())
                setDrawMarkers(true)
                setTransparentCircleAlpha(0)
                description.isEnabled = false

                legend.isEnabled = isLegendEnabled

                legend.form = Legend.LegendForm.CIRCLE
                legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
                legend.verticalAlignment = Legend.LegendVerticalAlignment.CENTER
                legend.orientation = Legend.LegendOrientation.VERTICAL
                legend.yEntrySpace = 15f
                legend.xOffset = PieChartHelper.PIE_CHART_LEGEND_X_OFFSET
                legend.textSize = 12f
                legend.formSize = 15f

                extraTopOffset = offsetTop
                extraLeftOffset = offsetLeft

                data = PieData(dataSet)

                invalidate()
            }
        })
}

object PieChartHelper {
    fun generateDataFromGrades(grades: SortedMap<String, Int>): PieDataSet {
        val entries = mutableListOf<PieEntry>()
        val colors = mutableListOf<Int>()

        for (grade in grades) {
            entries.add(PieEntry(grade.value.toFloat(), "Grade ${grade.key}"))
            colors.add(
                when (grade.key){
                    "A" -> android.graphics.Color.parseColor(ChartsHelper.GradeToColor.A.rgb)
                    "B" -> android.graphics.Color.parseColor(ChartsHelper.GradeToColor.B.rgb)
                    "C" -> android.graphics.Color.parseColor(ChartsHelper.GradeToColor.C.rgb)
                    else -> throw IllegalStateException("Invalid grade")
                }
            )
        }
        val dataSet = PieDataSet(entries, "")
        dataSet.colors = colors

        return dataSet
    }

    /** default offset on the chart */
    const val PIE_CHART_OFFSET_TOP = 0f
    /** default offset on the chart */
    const val PIE_CHART_OFFSET_LEFT = -50f

    /** default offset on the legend */
    const val PIE_CHART_LEGEND_X_OFFSET = 12.5f
}
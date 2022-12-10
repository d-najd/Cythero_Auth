package com.tradiebot.cythero.presentation.components.charts

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.DashPathEffect
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.size
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.renderer.LegendRenderer
import com.github.mikephil.charting.utils.ViewPortHandler
import com.tradiebot.cythero.domain.analytics.GradeEnum
import java.util.SortedMap

/**
 * Creates a PieChart which fills max size and gets centered, currently there is no way to align the
 * chart and doing so requires rework of some components in the library.
 *
 * @param dataSet data set for the chart
 * @param offsetLeft offset to the left on the chart in <b>DP</b>. use negative values for offset to the right
 * @param offsetTop offset to the top on the chart in <b>DP</b>. use negative values for offset to the bottom
 * @param isLegendEnabled whether the legend is enabled or not on by default. <b>NOTE</b> enabling the
 * legend changes the offset on the chart
 *
 * TODO while hovering a field in chart display number of overall grades
 *  in that category like in the webapp also round just the tip of the slices and this needs a rework
 *  and this is not reusable at its current state
 */
@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    dataSet: PieDataSet,
    offsetLeft: Float = PieChartHelper.PIE_CHART_OFFSET_LEFT,
    offsetTop: Float = PieChartHelper.PIE_CHART_OFFSET_TOP,
    isLegendEnabled: Boolean = true,
) {
    Row(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        /*
        AndroidView(
            modifier = modifier,
            factory = { context ->
                PieChart(context)
            },
            update = { pieChart ->
                dataSet.setDrawValues(false)
                dataSet.sliceSpace = 5f
                pieChart.data = PieData(dataSet)

                pieChart.isEnabled = false
                pieChart.setDrawEntryLabels(false)
                pieChart.setUsePercentValues(false)
                pieChart.setHoleColor(Color.Transparent.toArgb())
                pieChart.setDrawMarkers(true)
                pieChart.description.isEnabled = false
                pieChart.setTransparentCircleAlpha(0)

                pieChart.legend.isEnabled = false
                pieChart.invalidate()

                //pieChart.invalidate()

                /*
                pieChart.apply {
                    pieChart.isEnabled = false
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
                 */
            })
         */


        val legendEntries = arrayOf(
            LegendEntry("test", Legend.LegendForm.SQUARE, 25f, 50f, null, Color.Red.toArgb()),
            LegendEntry("test2", Legend.LegendForm.DEFAULT, 50f, 50f, null, Color.Blue.toArgb()),
            LegendEntry("test", Legend.LegendForm.SQUARE, 100f, 50f, null, Color.Green.toArgb()),
            LegendEntry("test", Legend.LegendForm.SQUARE, 200f, 50f, null, Color.Green.toArgb()),
            LegendEntry("test", Legend.LegendForm.CIRCLE, 300f, 50f, null, Color.Green.toArgb()),
        )
        val legend = Legend(legendEntries)
        legend.isEnabled = true
        legend.form = Legend.LegendForm.CIRCLE


        ComposeLegend(legend = legend)

        /*
        androidx.compose.foundation.Canvas(modifier = Modifier.fillMaxSize()
            .background(Color.Yellow)){
            val test = ViewPortHandler()
            test.setChartDimens(300f, 300f)
            test.setMinMaxScaleX(100f, 300f)
            test.setMinMaxScaleY(100f, 300f)

            val legendEntries = arrayOf(
                LegendEntry("test", Legend.LegendForm.CIRCLE, 50f, 50f, null, Color.Red.toArgb()),
                LegendEntry("test2", Legend.LegendForm.DEFAULT, 50f, 50f, null, Color.Blue.toArgb()),
                LegendEntry("test", Legend.LegendForm.LINE, 50f, 50f, null, Color.Green.toArgb()),
            )
            val legend = Legend(legendEntries)
            legend.isEnabled = true
            legend.form = Legend.LegendForm.CIRCLE

            LegendRenderer(test, legend)
        }
         */

    }

}

object PieChartHelper {
    @Composable
    fun generateDataFromGrades(grades: SortedMap<GradeEnum, Int>): PieDataSet {
        val entries = mutableListOf<PieEntry>()
        val colors = mutableListOf<Int>()

        for (grade in grades) {
            entries.add(PieEntry(grade.value.toFloat(), "Grade ${stringResource(grade.key.nameId)}"))
            colors.add(
                when (grade.key){
                    GradeEnum.A -> android.graphics.Color.parseColor(
                        GradeEnum.A.rgb)
                    GradeEnum.B -> android.graphics.Color.parseColor(
                        GradeEnum.B.rgb)
                    GradeEnum.C -> android.graphics.Color.parseColor(
                        GradeEnum.C.rgb)
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
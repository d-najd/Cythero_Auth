package com.tradiebot.cythero.presentation.util

import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineDataSet
import com.tradiebot.cythero.presentation.components.charts.LineChartHelper
import com.tradiebot.cythero.presentation.components.charts.PieChartHelper

object ChartsHelper {
    /**
     * copies and sets the values from the [sLegend] to [fLegend], if using line chart use [copyLegendAndFormatterVars]
     * @param [fLegend] the legend which is being modified
     * @param [sLegend] the legend which the values are being copied from
     * @see [copyLegendAndFormatterVars]
     */
    fun copyLegendVars(
        fLegend: Legend,
        sLegend: CytheroLegend
    ) {
        fLegend.isEnabled = sLegend.isEnabled

        fLegend.form = sLegend.form
        fLegend.horizontalAlignment = sLegend.horizontalAlignment
        fLegend.verticalAlignment = sLegend.verticalAlignment
        fLegend.orientation = sLegend.orientation
        fLegend.yEntrySpace = sLegend.yEntrySpace
        fLegend.xEntrySpace = sLegend.xEntrySpace
        fLegend.yOffset = sLegend.yOffset
        fLegend.xOffset = sLegend.xOffset
        fLegend.textSize = sLegend.textSize
        fLegend.formSize = sLegend.formSize
    }

    /**
     * extension of [copyLegendVars] which also applies value formatters
     * TODO there is probably a common interface, that can be used instead to apply the formatters
     *  instead
     */
    fun copyLegendAndFormatterVars(
        fLegend: Legend,
        sLegend: CytheroLegend,
        lineChart: LineChart,
        dataSet: LineDataSet = lineChart.data.dataSets[0] as LineDataSet
    ){
        if(sLegend.verticalValueFormatter != LineChartHelper.LineValueFormatterType.DEFAULT) {
            lineChart.xAxis.setValueFormatter { position, _ ->
                LineChartHelper.LineValueFormatter.format(
                    sLegend.verticalValueFormatter,
                    dataSet,
                    position
                )
            }
        }

        if(sLegend.leftValueFormatter != LineChartHelper.LineValueFormatterType.DEFAULT) {
            lineChart.axisLeft.setValueFormatter { position, _ ->
                LineChartHelper.LineValueFormatter.format(
                    sLegend.leftValueFormatter,
                    dataSet,
                    position
                )
            }
        }

        if(sLegend.rightValueFormatter != LineChartHelper.LineValueFormatterType.DEFAULT) {
            lineChart.axisRight.setValueFormatter { position, _ ->
                LineChartHelper.LineValueFormatter.format(
                    sLegend.rightValueFormatter,
                    dataSet,
                    position
                )
            }
        }

        lineChart.xAxis.position = sLegend.xAxisPosition
        copyLegendVars(fLegend, sLegend)
    }

    fun defaultPieCLegend(): CytheroLegend {
        val legend = CytheroLegend()
        legend.xOffset = PieChartHelper.PIE_CHART_LEGEND_X_OFFSET
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.verticalAlignment = Legend.LegendVerticalAlignment.CENTER
        legend.orientation = Legend.LegendOrientation.VERTICAL
        return legend
    }

    fun defaultLineCLegend(): CytheroLegend {
        val legend = CytheroLegend()

        legend.verticalValueFormatter = LineChartHelper.LineValueFormatterType.DEFAULT
        legend.leftValueFormatter = LineChartHelper.LineValueFormatterType.DEFAULT
        legend.rightValueFormatter = LineChartHelper.LineValueFormatterType.DEFAULT
        legend.xAxisPosition = XAxis.XAxisPosition.TOP

        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.orientation = Legend.LegendOrientation.HORIZONTAL

        return legend
    }
}


package com.tradiebot.cythero.presentation.util

import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.tradiebot.cythero.presentation.components.charts.LineChartHelper
import com.tradiebot.cythero.presentation.components.charts.PieChartHelper

// probably needs more appropriate name
class ChartSettingsHolder : Legend() {
    var xAxis: XAxis = XAxis()
    var yAxis: YAxis = YAxis()

    var xAxisValueFormatter:
            LineChartHelper.LineValueFormatterType = LineChartHelper.LineValueFormatterType.DEFAULT
    var leftValueFormatter:
            LineChartHelper.LineValueFormatterType = LineChartHelper.LineValueFormatterType.DEFAULT
    var rightValueFormatter:
            LineChartHelper.LineValueFormatterType = LineChartHelper.LineValueFormatterType.DEFAULT

    init {
        form = LegendForm.CIRCLE
        isEnabled = true
        formSize = 15f
        textSize = 12f
        xEntrySpace = 25f
        yEntrySpace = 15f
    }

    companion object {
        fun defaultPieCSettings(): ChartSettingsHolder {
            val legend = ChartSettingsHolder()
            legend.xOffset = PieChartHelper.PIE_CHART_LEGEND_X_OFFSET
            legend.horizontalAlignment = LegendHorizontalAlignment.RIGHT
            legend.verticalAlignment = LegendVerticalAlignment.CENTER
            legend.orientation = LegendOrientation.VERTICAL
            return legend
        }

        fun defaultBarLineCSettings(): ChartSettingsHolder {
            val legend = ChartSettingsHolder()

            legend.xAxisValueFormatter = LineChartHelper.LineValueFormatterType.DEFAULT
            legend.leftValueFormatter = LineChartHelper.LineValueFormatterType.DEFAULT
            legend.rightValueFormatter = LineChartHelper.LineValueFormatterType.DEFAULT
            legend.xAxis.position = XAxis.XAxisPosition.TOP

            legend.horizontalAlignment = LegendHorizontalAlignment.CENTER
            legend.verticalAlignment = LegendVerticalAlignment.BOTTOM
            legend.orientation = LegendOrientation.HORIZONTAL

            return legend
        }
    }
}
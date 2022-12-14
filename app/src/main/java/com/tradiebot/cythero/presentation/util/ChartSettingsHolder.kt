package com.tradiebot.cythero.presentation.util

import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.tradiebot.cythero.presentation.components.charts.LineChartHelper
import com.tradiebot.cythero.presentation.components.charts.PieChartHelper

// probably needs more appropriate name
class ChartSettingsHolder {
    var legend = Legend()
    var xAxis: XAxis = XAxis()
    var yAxis: YAxis = YAxis()

    var xAxisValueFormatter:
            LineChartHelper.LineValueFormatterType = LineChartHelper.LineValueFormatterType.DEFAULT
    var leftValueFormatter:
            LineChartHelper.LineValueFormatterType = LineChartHelper.LineValueFormatterType.DEFAULT
    var rightValueFormatter:
            LineChartHelper.LineValueFormatterType = LineChartHelper.LineValueFormatterType.DEFAULT

    init {
        legend.form = LegendForm.CIRCLE
        legend.isEnabled = true
        legend.formSize = 15f
        legend.textSize = 12f
        legend.xEntrySpace = 25f
        legend.yEntrySpace = 15f
    }

    companion object {
        fun defaultPieCSettings(): ChartSettingsHolder {
            val holder = ChartSettingsHolder()
            holder.legend.xOffset = PieChartHelper.PIE_CHART_LEGEND_X_OFFSET
            holder.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            holder.legend.verticalAlignment = Legend.LegendVerticalAlignment.CENTER
            holder.legend.orientation = Legend.LegendOrientation.VERTICAL
            return holder
        }

        fun defaultBarLineCSettings(): ChartSettingsHolder {
            val holder = ChartSettingsHolder()

            holder.xAxisValueFormatter = LineChartHelper.LineValueFormatterType.DEFAULT
            holder.leftValueFormatter = LineChartHelper.LineValueFormatterType.DEFAULT
            holder.rightValueFormatter = LineChartHelper.LineValueFormatterType.DEFAULT
            holder.xAxis.position = XAxis.XAxisPosition.TOP

            holder.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            holder.legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
            holder.legend.orientation = Legend.LegendOrientation.HORIZONTAL

            return holder
        }
    }
}
package com.tradiebot.cythero.presentation.util.chart

import androidx.compose.ui.geometry.Offset
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.tradiebot.cythero.presentation.components.chart.PieChartHelper

// probably needs more appropriate name
class ChartSettingsHolder {
    var legend = Legend()
    var xAxis: XAxis = XAxis()
    @Suppress("unused")
    var yAxis: YAxis = YAxis()
    var description = Description()
    
    var offsets: Offset = Offset(0f,0f)
    
    var xAxisValueFormatter:
            ChartValueFormatterType = ChartValueFormatterType.DEFAULT
    var leftValueFormatter:
            ChartValueFormatterType = ChartValueFormatterType.DEFAULT
    var rightValueFormatter:
            ChartValueFormatterType = ChartValueFormatterType.DEFAULT

    init {
        legend.form = LegendForm.CIRCLE
        legend.isEnabled = true
        legend.formSize = 15f
        legend.textSize = 12f
        legend.xEntrySpace = 25f
        legend.yEntrySpace = 15f
        
        description.isEnabled = false
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

            holder.xAxisValueFormatter = ChartValueFormatterType.DEFAULT
            holder.leftValueFormatter = ChartValueFormatterType.DEFAULT
            holder.rightValueFormatter = ChartValueFormatterType.DEFAULT
            
            holder.xAxis.position = XAxis.XAxisPosition.TOP
            holder.xAxis.axisMinimum = 0f
            holder.xAxis.axisMaximum = 0f

            holder.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            holder.legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
            holder.legend.orientation = Legend.LegendOrientation.HORIZONTAL

            return holder
        }
    }
}
package com.tradiebot.cythero.presentation.util.chart

import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis

// probably needs more appropriate name
class ChartSettingsHolder {
    var legend = Legend()
    var xAxis: XAxis = XAxis()
    @Suppress("unused")
    var yAxis: YAxis = YAxis()
    var description = Description()
    
    var topOffset = 0f
    var bottomOffset = 0f
    var leftOffset = 0f
    var rightOffset = 0f
    
    var xAxisValueFormatter:
            ChartValueFormatterType = ChartValueFormatterType.DEFAULT
    var leftValueFormatter:
            ChartValueFormatterType = ChartValueFormatterType.DEFAULT
    var rightValueFormatter:
            ChartValueFormatterType = ChartValueFormatterType.DEFAULT

    init {
        with(legend) {
            form = LegendForm.CIRCLE
            isEnabled = true
            formSize = 15f
            textSize = 12f
            xEntrySpace = 25f
            yEntrySpace = 15f
        }
        
        description.isEnabled = false
    }

    companion object {
        fun defaultPieCSettings() = ChartSettingsHolder().apply {
            leftOffset = -50f
            
            legend.xOffset = 5f
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            legend.verticalAlignment = Legend.LegendVerticalAlignment.CENTER
            legend.orientation = Legend.LegendOrientation.VERTICAL
        }

        fun defaultBarLineCSettings() = ChartSettingsHolder().apply {
            xAxisValueFormatter = ChartValueFormatterType.DEFAULT
            leftValueFormatter = ChartValueFormatterType.DEFAULT
            rightValueFormatter = ChartValueFormatterType.DEFAULT
    
            xAxis.position = XAxis.XAxisPosition.TOP
            xAxis.axisMinimum = 0f
            xAxis.axisMaximum = 0f
    
            legend.yEntrySpace = 20f
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
            legend.orientation = Legend.LegendOrientation.HORIZONTAL
        }
    }
}
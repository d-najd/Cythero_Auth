package com.tradiebot.cythero.presentation.util

import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.tradiebot.cythero.presentation.components.charts.LineChartHelper

class CytheroLegend() : Legend() {
    var verticalValueFormatter:
            LineChartHelper.LineValueFormatterType = LineChartHelper.LineValueFormatterType.DEFAULT
    var leftValueFormatter:
            LineChartHelper.LineValueFormatterType = LineChartHelper.LineValueFormatterType.DEFAULT
    var rightValueFormatter:
            LineChartHelper.LineValueFormatterType = LineChartHelper.LineValueFormatterType.DEFAULT

    var xAxisPosition:
            XAxis.XAxisPosition = XAxis.XAxisPosition.TOP

    init {
        form = LegendForm.CIRCLE
        isEnabled = true
        formSize = 15f
        textSize = 12f
        xEntrySpace = 25f
        yEntrySpace = 15f
    }

}
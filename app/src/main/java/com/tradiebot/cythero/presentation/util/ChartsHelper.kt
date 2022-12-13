package com.tradiebot.cythero.presentation.util

import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData
import com.github.mikephil.charting.data.ChartData
import com.github.mikephil.charting.interfaces.datasets.IDataSet
import com.tradiebot.cythero.presentation.components.charts.LineChartHelper
import com.tradiebot.cythero.util.convertPixelsToDp
import com.tradiebot.cythero.util.mAppContext



object ChartsHelper {
    /**
     * @param [legend] the legend which is being modified
     * @param [holder] the legend which the values are being copied from
     */
    fun copyIntoLegend(
        legend: Legend,
        holder: ChartFieldHolder
    ) {
        legend.isEnabled = holder.isEnabled

        legend.form = holder.form
        legend.horizontalAlignment = holder.horizontalAlignment
        legend.verticalAlignment = holder.verticalAlignment
        legend.orientation = holder.orientation
        legend.yEntrySpace = mAppContext().convertPixelsToDp(holder.yEntrySpace)
        legend.xEntrySpace = mAppContext().convertPixelsToDp(holder.xEntrySpace)
        legend.yOffset = mAppContext().convertPixelsToDp(holder.yOffset)
        legend.xOffset = mAppContext().convertPixelsToDp(holder.xOffset)
        legend.textSize = mAppContext().convertPixelsToDp(holder.textSize)
        legend.formSize = holder.formSize
    }

    fun <T: BarLineScatterCandleBubbleData<*>> copyIntoBarLineChart(
        chart: BarLineChartBase<T>,
        legend: Legend,
        holder: ChartFieldHolder,
        dataSet: IDataSet<*> = chart.data.dataSets[0]
    ) {
        chart.axisLeft.setValueFormatterIfNotDefault(
            type = holder.leftValueFormatter,
            dataSet = dataSet
        )

        chart.axisRight.setValueFormatterIfNotDefault(
            type = holder.rightValueFormatter,
            dataSet = dataSet
        )

        copyIntoChart(
            chart = chart,
            legend = legend,
            holder = holder,
            dataSet = dataSet
        )
    }

    fun <T: ChartData<*>> copyIntoChart(
        chart: Chart<T>,
        legend: Legend,
        holder: ChartFieldHolder,
        dataSet: IDataSet<*> = chart.data.dataSets[0]
    ) {

        chart.xAxis.setValueFormatterIfNotDefault(
            type = holder.xAxisValueFormatter,
            dataSet = dataSet
        )

        chart.xAxis.position = holder.xAxis.position

        copyIntoLegend(
            legend = legend,
            holder = holder
        )
    }

    private fun AxisBase.setValueFormatterIfNotDefault(
        type: LineChartHelper.LineValueFormatterType,
        dataSet: IDataSet<*>,
    ) {
        if(type == LineChartHelper.LineValueFormatterType.DEFAULT) return
        this.setValueFormatter { value, _ ->
            LineChartHelper.LineValueFormatter.format(
                type,
                dataSet,
                value
            )
        }
    }
}


package com.tradiebot.cythero.presentation.util

import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.charts.PieChart
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
        holder: ChartSettingsHolder
    ) {
        legend.isEnabled = holder.legend.isEnabled

        legend.form = holder.legend.form
        legend.horizontalAlignment = holder.legend.horizontalAlignment
        legend.verticalAlignment = holder.legend.verticalAlignment
        legend.orientation = holder.legend.orientation
        legend.yEntrySpace = mAppContext().convertPixelsToDp(holder.legend.yEntrySpace)
        legend.xEntrySpace = mAppContext().convertPixelsToDp(holder.legend.xEntrySpace)
        legend.yOffset = mAppContext().convertPixelsToDp(holder.legend.yOffset)
        legend.xOffset = mAppContext().convertPixelsToDp(holder.legend.xOffset)
        legend.textSize = mAppContext().convertPixelsToDp(holder.legend.textSize)
        legend.formSize = holder.legend.formSize
    }

    fun <T: BarLineScatterCandleBubbleData<*>> copyIntoBarLineChart(
        chart: BarLineChartBase<T>,
        legend: Legend,
        holder: ChartSettingsHolder,
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
        holder: ChartSettingsHolder,
        dataSet: IDataSet<*> = chart.data.dataSets[0]
    ) {
        if(chart !is PieChart) {
            chart.xAxis.setValueFormatterIfNotDefault(
                    type = holder.xAxisValueFormatter,
                    dataSet = dataSet
            )

            chart.xAxis.position = holder.xAxis.position
        }

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


package com.tradiebot.cythero.presentation.util.chart

import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData
import com.github.mikephil.charting.data.ChartData
import com.github.mikephil.charting.interfaces.datasets.IDataSet
import com.tradiebot.cythero.util.convertPixelsToDp
import com.tradiebot.cythero.util.mAppContext
import kotlin.math.abs

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
        with(chart) {
            extraTopOffset = if (holder.offsets.y > 0) holder.offsets.y else 0f
            extraBottomOffset = if (holder.offsets.y < 0) abs(holder.offsets.y) else 0f
            extraLeftOffset = if (holder.offsets.x > 0) holder.offsets.x else 0f
            extraRightOffset = if (holder.offsets.x < 0) abs(holder.offsets.x) else 0f
    
            // Pie chart doesn't have xAxis
            if(this !is PieChart) {
                xAxis.setValueFormatterIfNotDefault(
                    type = holder.xAxisValueFormatter,
                    dataSet = dataSet
                )
        
                xAxis.position = holder.xAxis.position
                xAxis.axisMaximum = holder.xAxis.axisMaximum
                xAxis.axisMinimum = holder.xAxis.axisMinimum
            }
    
            description.isEnabled = holder.description.isEnabled
        }
        
        copyIntoLegend(
            legend = legend,
            holder = holder
        )
    }

    private fun AxisBase.setValueFormatterIfNotDefault(
        type: ChartValueFormatterType,
        dataSet: IDataSet<*>,
    ) {
        if(type == ChartValueFormatterType.DEFAULT) return
        setValueFormatter { value, _ ->
            ChartValueFormatter.format(
                type,
                dataSet,
                value
            )
        }
    }
}


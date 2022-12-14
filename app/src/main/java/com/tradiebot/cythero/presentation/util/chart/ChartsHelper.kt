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

@Suppress("MemberVisibilityCanBePrivate")
object ChartsHelper {
    /**
     * @param [legend] the legend which is being modified
     * @param [holder] the legend which the values are being copied from
     */
    fun copyIntoLegend(
        legend: Legend,
        holder: ChartSettingsHolder
    ) {
        with(legend) {
            isEnabled = holder.legend.isEnabled
    
            form = holder.legend.form
            horizontalAlignment = holder.legend.horizontalAlignment
            verticalAlignment = holder.legend.verticalAlignment
            orientation = holder.legend.orientation
            yEntrySpace = mAppContext().convertPixelsToDp(holder.legend.yEntrySpace)
            xEntrySpace = mAppContext().convertPixelsToDp(holder.legend.xEntrySpace)
            yOffset = mAppContext().convertPixelsToDp(holder.legend.yOffset)
            xOffset = mAppContext().convertPixelsToDp(holder.legend.xOffset)
            textSize = mAppContext().convertPixelsToDp(holder.legend.textSize)
            formSize = holder.legend.formSize
        }
    }

    fun <T: BarLineScatterCandleBubbleData<*>> copyIntoBarLineChart(
        chart: BarLineChartBase<T>,
        holder: ChartSettingsHolder,
        dataSet: IDataSet<*> = chart.data.dataSets[0]
    ) {
        with(chart) {
            axisLeft.setValueFormatterIfNotDefault(
                type = holder.leftValueFormatter,
                dataSet = dataSet
            )
    
            axisRight.setValueFormatterIfNotDefault(
                type = holder.rightValueFormatter,
                dataSet = dataSet
            )
        }

        copyIntoChart(
            chart = chart,
            holder = holder,
            dataSet = dataSet
        )
    }

    fun <T: ChartData<*>> copyIntoChart(
        chart: Chart<T>,
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
            legend = chart.legend,
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


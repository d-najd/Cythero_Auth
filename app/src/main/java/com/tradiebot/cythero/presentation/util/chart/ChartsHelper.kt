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
            // Setting the offset for the pie-chart the "intended" way makes it smaller, guess somebody doesn't understand the meaning of offset...
            extraTopOffset.takeIf { holder.offsets.y > 0 && chart !is PieChart }.apply { holder.offsets.y }
            extraBottomOffset.takeIf { holder.offsets.y < 0 && chart !is PieChart }.apply { abs(holder.offsets.y) }
            extraLeftOffset.takeIf { holder.offsets.x > 0 && chart !is PieChart }.apply { holder.offsets.x }
            extraRightOffset.takeIf { holder.offsets.x < 0 && chart !is PieChart }.apply { abs(holder.offsets.x) }
    
            // Pie chart doesn't have xAxis
            if(this !is PieChart) {
                xAxis.setValueFormatterIfNotDefault(
                    type = holder.xAxisValueFormatter,
                    dataSet = dataSet
                )
                
                xAxis.position = holder.xAxis.position
                xAxis.axisMaximum = holder.xAxis.axisMaximum
                xAxis.axisMinimum = holder.xAxis.axisMinimum
            } else {
                extraTopOffset = holder.offsets.y
                extraLeftOffset = holder.offsets.x
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


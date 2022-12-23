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

object ChartsHelper {
    /**
     * @param [legend] the legend which is being modified
     * @param [holder] the legend which the values are being copied from
     */
    private fun copyIntoLegend(
        legend: Legend,
        holder: ChartSettingsHolder
    ) {
        val hLegend = holder.legend
        
        with(legend) {
            isEnabled = hLegend.isEnabled
    
            form = hLegend.form
            horizontalAlignment = hLegend.horizontalAlignment
            verticalAlignment = hLegend.verticalAlignment
            orientation = hLegend.orientation
            
            yOffset = mAppContext().convertPixelsToDp(hLegend.yOffset)
            xOffset = mAppContext().convertPixelsToDp(hLegend.xOffset)
            
            textSize = mAppContext().convertPixelsToDp(hLegend.textSize)
            formSize = hLegend.formSize
    
            yEntrySpace = mAppContext().convertPixelsToDp(hLegend.yEntrySpace)
            xEntrySpace = mAppContext().convertPixelsToDp(hLegend.xEntrySpace)
            
            mNeededHeight = hLegend.mNeededHeight
            mNeededWidth = hLegend.mNeededWidth
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
            extraTopOffset = holder.topOffset
            extraBottomOffset = holder.bottomOffset
            extraRightOffset = holder.rightOffset
            extraLeftOffset = holder.leftOffset
    
            // Pie chart doesn't have xAxis
            if(this !is PieChart) {
                xAxis.setValueFormatterIfNotDefault(
                    type = holder.xAxisValueFormatter,
                    dataSet = dataSet
                )
                
                xAxis.position = holder.xAxis.position
                xAxis.axisMaximum = holder.xAxis.axisMaximum
                xAxis.axisMinimum = holder.xAxis.axisMinimum
                xAxis.yOffset = holder.xAxis.yOffset
                xAxis.xOffset = holder.xAxis.xOffset
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


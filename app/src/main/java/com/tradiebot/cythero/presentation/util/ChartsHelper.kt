package com.tradiebot.cythero.presentation.util

import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData
import com.github.mikephil.charting.data.ChartData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.LineRadarDataSet
import com.tradiebot.cythero.presentation.components.charts.LineChartHelper
import com.tradiebot.cythero.presentation.components.charts.PieChartHelper
import com.tradiebot.cythero.util.convertPixelsToDp
import com.tradiebot.cythero.util.mAppContext



object ChartsHelper {
    /**
     * copies and sets the values from the [holder] to [legend], if using line chart use [copyLegendAndFormatterVars]
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


    fun <T: BarLineScatterCandleBubbleData<*>> copyIntoBarChart(
        chart: BarLineChartBase<T>,
        legend: Legend,
        holder: ChartFieldHolder,
        dataSet: LineRadarDataSet<*> = chart.data.dataSets[0]
    ) {
        chart.xAxis.setValueFormatterIfNotDefault(
            type = holder.verticalValueFormatter,
            dataset = dataSet
        )

        chart.axisLeft.setValueFormatterIfNotDefault(
            type = holder.verticalValueFormatter,
            dataset = dataSet
        )

        chart.axisRight.setValueFormatterIfNotDefault(
            type = holder.verticalValueFormatter,
            dataset = dataSet
        )

        chart.xAxis.position = holder.xAxisPosition

        copyIntoLegend(
            legend = legend,
            holder = holder
        )
    }

    private fun <T: ChartData<*>> copyIntoChart(
        chart: Chart<T>,
        legend: Legend,
        holder: ChartFieldHolder,
        dataSet: LineDataSet = chart.data.dataSets[0] as LineDataSet
    ) {

    }

    fun AxisBase.setValueFormatterIfNotDefault(
        type: LineChartHelper.LineValueFormatterType,
        dataset: LineRadarDataSet<*>,
    ) {
        if(type == LineChartHelper.LineValueFormatterType.DEFAULT) return
        this.setValueFormatter { value, _ ->
            LineChartHelper.LineValueFormatter.format(
                type,
                dataset,
                value
            )
        }
    }

    /*
    /**
     * extension of [copyLegendVars] which also applies value formatters
     * TODO there is probably a common interface, that can be used instead to apply the formatters
     *  instead
     */
    fun copyLegendAndFormatterVars(
        fLegend: Legend,
        sLegend: CytheroLegend,
        lineChart: LineChart,
        dataSet: LineDataSet = lineChart.data.dataSets[0] as LineDataSet
    ){
        if(sLegend.verticalValueFormatter != LineChartHelper.LineValueFormatterType.DEFAULT) {
            lineChart.xAxis.setValueFormatter { position, _ ->
                LineChartHelper.LineValueFormatter.format(
                    sLegend.verticalValueFormatter,
                    dataSet,
                    position
                )
            }
        }

        if(sLegend.leftValueFormatter != LineChartHelper.LineValueFormatterType.DEFAULT) {
            lineChart.axisLeft.setValueFormatter { position, _ ->
                LineChartHelper.LineValueFormatter.format(
                    sLegend.leftValueFormatter,
                    dataSet,
                    position
                )
            }
        }

        if(sLegend.rightValueFormatter != LineChartHelper.LineValueFormatterType.DEFAULT) {
            lineChart.axisRight.setValueFormatter { position, _ ->
                LineChartHelper.LineValueFormatter.format(
                    sLegend.rightValueFormatter,
                    dataSet,
                    position
                )
            }
        }

        lineChart.xAxis.position = sLegend.xAxisPosition
        copyLegendVars(fLegend, sLegend)
    }
     */

    fun defaultPieCLegend(): ChartFieldHolder {
        val legend = ChartFieldHolder()
        legend.xOffset = PieChartHelper.PIE_CHART_LEGEND_X_OFFSET
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.verticalAlignment = Legend.LegendVerticalAlignment.CENTER
        legend.orientation = Legend.LegendOrientation.VERTICAL
        return legend
    }

    fun defaultLineCLegend(): ChartFieldHolder {
        val legend = ChartFieldHolder()

        legend.verticalValueFormatter = LineChartHelper.LineValueFormatterType.DEFAULT
        legend.leftValueFormatter = LineChartHelper.LineValueFormatterType.DEFAULT
        legend.rightValueFormatter = LineChartHelper.LineValueFormatterType.DEFAULT
        legend.xAxisPosition = XAxis.XAxisPosition.TOP

        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.orientation = Legend.LegendOrientation.HORIZONTAL

        return legend
    }
}


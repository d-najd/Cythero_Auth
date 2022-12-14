package com.tradiebot.cythero.presentation.components.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.tradiebot.cythero.presentation.util.chart.ChartSettingsHolder
import com.tradiebot.cythero.presentation.util.chart.ChartsHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking

/**
 * TODO anything above 10 parameters is unacceptable
 */
@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    dataSets: Flow<List<LineDataSet>>,
    bezierType: LineDataSet.Mode = LineDataSet.Mode.HORIZONTAL_BEZIER,
    pinchZoomEnabled: Boolean = false,
    dragEnabled: Boolean = false,
    scaleEnabled: Boolean = false,
    
    labelCount: Int = 9,
    lineWidth: Float = 2f,
    circleRadius: Float = 4f,
    drawCircleHole: Boolean = false,
    drawValues: Boolean = true,
    
    chartSettingsHolder: ChartSettingsHolder = ChartSettingsHolder.defaultBarLineCSettings()
) {
    AndroidView(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Transparent),
        factory = { context ->
            LineChart(context)
        },

        update = { lineChart ->
            with(lineChart) {
                var curDataSet: LineDataSet
                runBlocking {
                    curDataSet = dataSets.last()[0]
                }

                isDragEnabled = dragEnabled
                setPinchZoom(pinchZoomEnabled)
                setScaleEnabled(scaleEnabled)

                ChartsHelper.copyIntoBarLineChart(
                    chart = this,
                    holder = chartSettingsHolder,
                    dataSet = curDataSet
                )

                runBlocking {
                    dataSets.collectLatest {
                        val dataSetsLatest = dataSets.last()
                        curDataSet = dataSets.last()[0]

                        xAxis.axisMaximum = if(curDataSet.entryCount > labelCount) labelCount.toFloat() else curDataSet.entryCount.toFloat() - 1f
                        xAxis.labelCount = if(curDataSet.entryCount > labelCount) labelCount else curDataSet.entryCount - 1

                        for(dataSet in dataSetsLatest){
                            dataSet.lineWidth = lineWidth
                            dataSet.circleRadius = circleRadius
                            dataSet.cubicIntensity = 0.025f
                            dataSet.setDrawCircleHole(drawCircleHole)
                            dataSet.setDrawValues(drawValues)
                            dataSet.mode = bezierType
                        }

                        data = LineData(dataSetsLatest)

                        invalidate()
                    }
                }
            }
        }
    )
}

object LineChartHelper{
    /**
     * General [LineDataSet] generator
     *
     * @param data list of pairs where [Pair.first] is float representing the height of the entry
     * and [Pair.second] representing the name of the entry
     * @param color color of the dataset
     * @param label label of the dataset
     * @return dataset
     */
    fun generateDataSet(
        data: List<Pair<Float, String>>,
        color: String,
        label: String = "",
    ): LineDataSet {
        val entries = mutableListOf<Entry>()

        for ((index, part) in data.withIndex()) {
            entries.add(Entry(index.toFloat(), part.first, part.second))
        }

        val dataSet = LineDataSet(entries, label)
        dataSet.color = android.graphics.Color.parseColor(color)
        dataSet.setCircleColor(android.graphics.Color.parseColor(color))

        return dataSet
    }
}

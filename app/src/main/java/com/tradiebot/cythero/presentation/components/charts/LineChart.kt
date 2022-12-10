package com.tradiebot.cythero.presentation.components.charts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.*
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.util.view.ContextHolder
import com.tradiebot.cythero.domain.analytics.user.model.AnalyticsUser
import com.tradiebot.cythero.domain.analytics.Grade
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import kotlin.math.roundToInt

/**
 * TODO just look at the number of parameters
 */
@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    dataSets: Flow<List<LineDataSet>>,
    offsets: Offset = Offset(0f, 0f),
    bezierType: LineDataSet.Mode = LineDataSet.Mode.HORIZONTAL_BEZIER,
    pinchZoomEnabled: Boolean = false,
    dragEnabled: Boolean = false,
    scaleEnabled: Boolean = false,

    labelCount: Int = 9,
    lineWidth: Float = 2f,
    circleRadius: Float = 4f,
    drawCircleHole: Boolean = false,
    drawValues: Boolean = true,

    horizontalValueFormatter: LineChartHelper.LineValueFormatterType = LineChartHelper.LineValueFormatterType.DEFAULT,
    leftValueFormatter: LineChartHelper.LineValueFormatterType = LineChartHelper.LineValueFormatterType.DEFAULT,
    rightValueFormatter: LineChartHelper.LineValueFormatterType = LineChartHelper.LineValueFormatterType.DEFAULT,
    xAxisPosition: XAxisPosition = XAxisPosition.TOP,

    //Legend
    isLegendEnabled: Boolean = true,
    legendForm: Legend.LegendForm = Legend.LegendForm.CIRCLE,
    legendFormSize: Float = 15f,
    legendTextSize: Float = 12f,
    legendOffsets: Offset = Offset(0f, 0f),
    legendEntrySpacing: Offset = Offset(25f,15f),
    legendHorizontalAlignment: Legend.LegendHorizontalAlignment = Legend.LegendHorizontalAlignment.CENTER,
    legendVerticalAlignment: Legend.LegendVerticalAlignment = Legend.LegendVerticalAlignment.BOTTOM,
    legendOrientation: Legend.LegendOrientation = Legend.LegendOrientation.HORIZONTAL,
) {
    AndroidView(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Transparent),
        factory = { context ->
            LineChart(context)
        },

        update = { lineChart ->
            lineChart.apply {
                var lastDataSet: LineDataSet
                runBlocking {
                    lastDataSet = dataSets.last()[0]
                }

                xAxis.axisMaximum = 0f
                xAxis.axisMinimum = 0f
                xAxis.axisMaximum = labelCount.toFloat()
                xAxis.labelCount = labelCount

                description.isEnabled = false

                isDragEnabled = dragEnabled
                setPinchZoom(pinchZoomEnabled)
                setScaleEnabled(scaleEnabled)

                maxHighlightDistance = 250f

                legend.isEnabled = isLegendEnabled

                legend.form = legendForm
                legend.horizontalAlignment = legendHorizontalAlignment
                legend.verticalAlignment = legendVerticalAlignment
                legend.orientation = legendOrientation
                legend.yEntrySpace = legendEntrySpacing.y
                legend.xEntrySpace = legendEntrySpacing.x
                legend.textSize = legendTextSize
                legend.formSize = legendFormSize

                xAxis.position = xAxisPosition

                if(horizontalValueFormatter != LineChartHelper.LineValueFormatterType.DEFAULT) {
                    xAxis.setValueFormatter { position, _ ->
                        LineChartHelper.LineValueFormatter.format(
                            horizontalValueFormatter,
                            lastDataSet,
                            position
                        )
                    }
                }

                if(leftValueFormatter != LineChartHelper.LineValueFormatterType.DEFAULT) {
                    axisLeft.setValueFormatter { position, _ ->
                        LineChartHelper.LineValueFormatter.format(
                            horizontalValueFormatter,
                            lastDataSet,
                            position
                        )
                    }
                }

                if(rightValueFormatter != LineChartHelper.LineValueFormatterType.DEFAULT) {
                    axisRight.setValueFormatter { position, _ ->
                        LineChartHelper.LineValueFormatter.format(
                            horizontalValueFormatter,
                            lastDataSet,
                            position
                        )
                    }
                }

                runBlocking {

                    dataSets.collectLatest {
                        val dataSetsLatest = dataSets.last()
                        lastDataSet = dataSets.last()[0]

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
    enum class LineValueFormatterType{
        /** formatted { value position } ex { Fender 2 } */
        VALUE_POSITION,
        /** doesn't format the value */
        DEFAULT,
    }

    object LineValueFormatter {
        fun format(
            type: LineValueFormatterType,
            dataSet: LineDataSet,
            position: Float
        ): String {
            @Suppress("KotlinConstantConditions")
            when (type) {
                LineValueFormatterType.VALUE_POSITION -> {
                    return valuePosition(
                        dataSet,
                        position,
                    )
                }
                LineValueFormatterType.DEFAULT -> {
                    throw IllegalStateException()
                }
            }
        }

        private fun valuePosition(dataSet: LineDataSet, position: Float): String{
            return "${position.toInt() + 1}" +
                    " ${(dataSet.entries?.getOrNull(position.toInt())?.data) ?: ""}"
        }
    }

    fun generatePartTimeTakenData(
        timeList: List<Int>,
        dates: List<String>,
    ): List<LineDataSet> {

        val formattedList = timeList.takeLast(10)
            .map { o -> o/60f }
            .zip(dates)

        val dataSet = generateDataSet(
            data = formattedList,
            label = Injekt.get<ContextHolder>().getString(R.string.field_sessions),
            color = Grade.A.rgb
        )

        return listOf(dataSet)
    }

    fun generateUserPartsData(
        analyticsUser: AnalyticsUser
    ): List<LineDataSet> {
        val analyticsTable = analyticsUser.analyticsUserTable

        val parts = analyticsTable.part.takeLast(10).map { Injekt.get<ContextHolder>().getString(it.nameId) }

        val lowCoverage = analyticsTable.clearLowCoverage.takeLast(10)
            .zip( analyticsTable.baseLowCoverage.takeLast(10)) { f, s -> f + s  }
            .zip( analyticsTable.primerLowCoverage.takeLast(10)) { f, s -> (f + s)/3 }
            .map { o -> o.roundToInt().toFloat() }
            .zip(parts)

        val goodCoverage = analyticsTable.clearGoodCoverage.takeLast(10)
            .zip( analyticsTable.baseGoodCoverage.takeLast(10)) { f, s -> f + s  }
            .zip( analyticsTable.primerGoodCoverage.takeLast(10)) { f, s -> (f + s)/3 }
            .map { o -> o.roundToInt().toFloat() }
            .zip(parts)

        val highCoverage = analyticsTable.clearHighCoverage.takeLast(10)
            .zip( analyticsTable.baseHighCoverage.takeLast(10)) { f, s -> f + s  }
            .zip( analyticsTable.primerHighCoverage.takeLast(10)) { f, s -> (f + s)/3 }
            .map { o -> o.roundToInt().toFloat() }
            .zip(parts)

        val lowCoverageDataSet = generateDataSet(
            data = lowCoverage,
            label = Injekt.get<ContextHolder>().getString(R.string.field_coverage_low),
            color = Grade.B.rgb,
        )

        val goodCoverageDataSet = generateDataSet(
            data = goodCoverage,
            label = Injekt.get<ContextHolder>().getString(R.string.field_coverage_good),
            color = Grade.A.rgb,
        )

        val highCoverageDataSet  = generateDataSet(
            data = highCoverage,
            label = Injekt.get<ContextHolder>().getString(R.string.field_coverage_high),
            color = Grade.C.rgb,
        )

        return listOf(lowCoverageDataSet, goodCoverageDataSet, highCoverageDataSet)

    }

    /**
     * general [LineDataSet] generator,
     *
     * @param data list of pairs where the "first" is float representing the height of the entry
     * and the "second" representing the name of the entry
     * @param color color of the dataset, if undefined
     * @param label label of the entry set
     */
    fun generateDataSet(
        data: List<Pair<Float, Any>>,
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
package com.tradiebot.cythero.presentation.components.chart

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.tradiebot.cythero.domain.analytics.Grade
import com.tradiebot.cythero.presentation.util.chart.ChartSettingsHolder
import com.tradiebot.cythero.presentation.util.chart.ChartsHelper
import com.tradiebot.cythero.util.mAppContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import java.util.*

/**
 * Creates a PieChart which fills max size and gets centered, currently there is no way to align the
 * chart and doing so requires rework of some components in the library.
 *
 * @param dataSet data set for the chart
 * legend changes the offset on the chart
 *
 * TODO while hovering a field in chart display number of overall grades
 *  in that category like in the webapp also round just the tip of the slices and this needs a rework
 *  and this is not reusable at its current state
 */
@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    dataSet: Flow<PieDataSet>,
    sliceSize: Float = 5f,
    
    chartSettingsHolder: ChartSettingsHolder = ChartSettingsHolder.defaultPieCSettings(),
) {
    AndroidView(
        modifier = modifier
            .fillMaxSize(),
        factory = { context ->
            PieChart(context)
        },

        update = { pieChart ->
            with(pieChart) {
                var curDataSet: PieDataSet
                runBlocking {
                    curDataSet = dataSet.last()
                }

                setDrawEntryLabels(false)
                setUsePercentValues(false)
                setHoleColor(Color.Transparent.toArgb())
                setDrawMarkers(true)
                setTransparentCircleAlpha(0)

                ChartsHelper.copyIntoChart(
                    chart = this,
                    holder = chartSettingsHolder,
                    dataSet = curDataSet
                )

                runBlocking {
                    dataSet.collectLatest {
                        curDataSet = dataSet.last()
                        curDataSet.setDrawValues(true)
                        curDataSet.sliceSpace = sliceSize
                        data = PieData(curDataSet)

                        invalidate()
                    }
                }
            }
        })
}

object PieChartHelper {
    
    /**
     * [PieDataSet] generator from given [Grade] map, this generator does not check if [Map.Entry.value]
     * is empty or not, the colors are generated from [Grade.rgb]
     * @param grades map where [Map.Entry.key] is the label of the entry and [Map.Entry.value]
     * is the value
     * @return dataset
     */
    fun dataFromGrades(grades: SortedMap<Grade, Int>): PieDataSet {
        val entries = mutableListOf<PieEntry>()
        val colors = mutableListOf<Int>()

        for (grade in grades) {
            entries.add(PieEntry(grade.value.toFloat(), "Grade ${mAppContext().getString(grade.key.nameId)}"))
            colors.add(
                when (grade.key){
                    Grade.A -> android.graphics.Color.parseColor(Grade.A.rgb)
                    Grade.B -> android.graphics.Color.parseColor(Grade.B.rgb)
                    Grade.C -> android.graphics.Color.parseColor(Grade.C.rgb)
                    else -> throw IllegalStateException("Invalid grade")
                }
            )
        }
        val dataSet = PieDataSet(entries, "")
        dataSet.colors = colors

        return dataSet
    }
    
    /**
     * General [PieDataSet] generator which includes values that are positive
     *
     * @param data list of pairs where [Pair.first] is float representing the height of the entry
     * and [Pair.second] representing the name of the entry, if [Pair.first] is negative the data for
     * that entry and the color will not be included in the dataset
     * @param colors colors of the dataset, the color will not be included if the an entry from the
     * [data] list does not exist or it's [Pair.first] is not positive
     * @param label label of the dataset
     * @return dataset which contains only positive values
     */
    fun generateDataSetPositive(
        data: List<Pair<Float, String>>,
        colors: List<String>,
        label: String = "",
    ): PieDataSet {
        val mColors = mutableListOf<Int>()
        val mEntries = mutableListOf<PieEntry>()
        if(colors.size < data.size) throw IllegalArgumentException()
        for(i in data.indices){
            if(data[i].first > 0){
                mEntries.add(PieEntry(data[i].first, data[i].second))
                mColors.add(android.graphics.Color.parseColor(colors[i]))
            }
        }

        val dataSet = PieDataSet(mEntries, label)
        dataSet.colors = mColors

        return dataSet
    }

    /** default offset on the chart */
    const val PIE_CHART_OFFSET_TOP = 0f
    /** default offset on the chart */
    const val PIE_CHART_OFFSET_LEFT = -50f

    /** default offset on the legend */
    const val PIE_CHART_LEGEND_X_OFFSET = 12.5f
}
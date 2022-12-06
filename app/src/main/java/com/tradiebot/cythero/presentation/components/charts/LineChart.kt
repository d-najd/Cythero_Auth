package com.tradiebot.cythero.presentation.components.charts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import com.tradiebot.cythero.domain.analytics.model.Analytics
import com.tradiebot.cythero.presentation.util.ChartsHelper

@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    dataSet: List<LineDataSet>,
    // offsetLeft: Float = PieChartHelper.PIE_CHART_OFFSET_LEFT,
    // offsetTop: Float = PieChartHelper.PIE_CHART_OFFSET_TOP,
    isLegendEnabled: Boolean = true,
){
    AndroidView(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Transparent),
        factory = { context ->
            LineChart(context)
        },

        update = { lineChart ->
            lineChart.apply {
                data = LineData(dataSet)


                // lineChart.setGridBackgroundColor(Color.Blue.toArgb())


                invalidate()
            }
        }
    )
}

object LineChartHelper{
    fun generatePartsDataSet(
        analytics: Analytics
    ): List<LineDataSet> {
        val analyticsTable = analytics.analyticsTable

        val parts = analyticsTable.part.takeLast(10)

        val lowCoverage = analyticsTable.clearLowCoverage.takeLast(10)
            .zip( analyticsTable.baseLowCoverage.takeLast(10)) { a, b -> a + b  }
            .zip( analyticsTable.primerLowCoverage.takeLast(10)) { a, b -> (a + b)/3 }
            .map { o -> o.toFloat() }
            .zip(parts)

        val goodCoverage = analyticsTable.clearGoodCoverage.takeLast(10)
            .zip( analyticsTable.baseGoodCoverage.takeLast(10)) { a, b -> a + b  }
            .zip( analyticsTable.primerGoodCoverage.takeLast(10)) { a, b -> (a + b)/3 }
            .map { o -> o.toFloat() }
            .zip(parts)

        val highCoverage = analyticsTable.clearHighCoverage.takeLast(10)
            .zip( analyticsTable.baseHighCoverage.takeLast(10)) { a, b -> a + b  }
            .zip( analyticsTable.primerHighCoverage.takeLast(10)) { a, b -> (a + b)/3 }
            .map { o -> o.toFloat() }
            .zip(parts)

        val lowCoverageDataSet = generateDataSet(
            data = lowCoverage,
            color = ChartsHelper.GradeToColor.C.rgb,
        )

        val goodCoverageDataSet = generateDataSet(
            data = goodCoverage,
            color = ChartsHelper.GradeToColor.B.rgb,
        )

        val highCoverageDataSet  = generateDataSet(
            data = highCoverage,
            color = ChartsHelper.GradeToColor.A.rgb,
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
        data: List<Pair<Float, String>>,
        color: String = ChartsHelper.GradeToColor.DEFAULT.toString(),
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





/*
@Composable
@Deprecated("This class needs rework if its to be used")
fun LineChartComponent(
    timeline: Timeline = Timeline.testingInstance(),
) {
    Text(text = "Example Line Chart")

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context -> LineChart(context) },
        update = { chart ->
            val values = timeline.timeline.mapToEntry()

            val dataSet = LineDataSet(values, "TimeLine Data Set")

            chart.apply {
                data = LineData(dataSet)
                invalidate()
            }
        }
    )
}
 */

/*
/**
 * converts TimelineEntry to charting Entry
 *
 * @return list of charting Entries
 */
private fun List<TimelineEntry>.mapToEntry(): List<Entry> {
    return this.map { tEntry -> Entry(this.indexOf(tEntry).toFloat(), tEntry.time_spent, tEntry.session_start ) }

    /*
    val dbTracks = map { it.toDbTrack() }
    val loggedServices = Injekt.get<TrackManager>().services.filter { it.isLogged }
    val source = Injekt.get<SourceManager>().getOrStub(sourceId)
    return loggedServices
        // Map to TrackItem
        .map { service -> TrackItem(dbTracks.find { it.sync_id.toLong() == service.id }, service) }
        // Show only if the service supports this manga's source
        .filter { (it.service as? EnhancedTrackService)?.accept(source) ?: true }

     */
}

 */

/*
@Composable
fun PieChartComponent() {

    Box {
        Text(text = "Pie Chart Below")

        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                BarChart(context)
            },
            update = { barChart ->
                val pieEntry1 = BarEntry(
                    10f,
                    5f,
                )

                val pieEntry2 = BarEntry(
                    15f,
                    10f,
                )

                val newList = mutableListOf<BarEntry>()

                newList.add(pieEntry1)
                newList.add(pieEntry2)

                val dataSet = BarDataSet(
                    newList,
                    "test",
                )
                val barData = BarData(dataSet)

                barChart.apply {
                    data = barData
                    invalidate()
                }


            })
    }
}
*/
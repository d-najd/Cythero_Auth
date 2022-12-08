package com.tradiebot.cythero.presentation.components.charts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*
import com.tradiebot.cythero.R
import com.tradiebot.cythero.domain.analytics.user.model.AnalyticsUser
import com.tradiebot.cythero.domain.analytics.user.model.GradeHelper

/**
 * TODO this is not reusable at its current state
 */
@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    dataSets: List<LineDataSet>,
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

                for(dataSet in dataSets) {
                    dataSet.lineWidth = 2f
                    dataSet.circleRadius = 4f
                    dataSet.cubicIntensity = .1f
                    dataSet.setDrawCircleHole(false)
                    dataSet.setDrawValues(true)

                    dataSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
                }

                data = LineData(dataSets)

                lineChart.xAxis.axisMinimum = 0f
                lineChart.xAxis.axisMaximum = 9f
                lineChart.xAxis.setValueFormatter { value, _ -> "${value.toInt() + 1}" +
                        " ${(dataSets[0].entries.getOrNull(value.toInt())?.data) ?: ""}"}
                lineChart.xAxis.labelCount = 9

                description.isEnabled = false

                setPinchZoom(false)

                isDragEnabled = false
                setScaleEnabled(false)

                maxHighlightDistance = 250f

                legend.isEnabled = isLegendEnabled

                legend.form = Legend.LegendForm.CIRCLE
                legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
                legend.orientation = Legend.LegendOrientation.HORIZONTAL
                legend.yEntrySpace = 15f
                legend.xEntrySpace = 25f
                legend.textSize = 12f
                legend.formSize = 15f

                invalidate()
            }
        }
    )
}

object LineChartHelper{
    @Composable
    fun generatePartsDataSet(
        analyticsUser: AnalyticsUser
    ): List<LineDataSet> {
        val analyticsTable = analyticsUser.analyticsUserTable

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
            label = stringResource(R.string.field_coverage_low),
            color = GradeHelper.C.rgb,
        )

        val goodCoverageDataSet = generateDataSet(
            data = goodCoverage,
            label = stringResource(R.string.field_coverage_good),
            color = GradeHelper.B.rgb,
        )

        val highCoverageDataSet  = generateDataSet(
            data = highCoverage,
            label = stringResource(R.string.field_coverage_high),
            color = GradeHelper.A.rgb,
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
    @Composable
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
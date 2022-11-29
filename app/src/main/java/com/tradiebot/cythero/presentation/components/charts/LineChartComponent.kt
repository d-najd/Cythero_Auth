package com.tradiebot.cythero.presentation.components.charts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.tradiebot.cythero.domain.timeline.model.Timeline
import com.tradiebot.cythero.domain.timeline.model.TimelineEntry

@Composable
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

/**
 * converts TimelineEntry to charting Entry
 *
 * TODO avoid this.indexOf() call
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
package com.tradiebot.cythero.presentation.components.charts

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

@Composable
fun PieChartComponent(
    pieDataSet: PieDataSet,
) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            PieChart(context)
        },
        update = { pieChart ->
            pieChart.apply {
                data = PieData(pieDataSet)
                pieChart.setDrawEntryLabels(false)
                pieChart.setUsePercentValues(false)
                invalidate()
            }
        })
}

object PieChartHelper {


    fun generateDataFromGrades(grades: Map<String, Int>): PieDataSet {
        val pieEntries = mutableListOf<PieEntry>()
        val colors = mutableListOf<Int>()

        for (grade in grades) {
            pieEntries.add(PieEntry(grade.value.toFloat(), grade.key))
            colors.add(
                when (grade.key){
                    "A" -> Color.Green.toArgb()
                    "B" -> Color.Blue.toArgb()
                    "C" -> Color.Red.toArgb()
                    else -> throw IllegalStateException("Invalid grade")
                }
            )
        }
        val pieDataSet = PieDataSet(pieEntries, "Grades")
        pieDataSet.sliceSpace = 5f
        pieDataSet.colors = colors

        return pieDataSet
    }
}
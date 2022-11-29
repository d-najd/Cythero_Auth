package com.tradiebot.cythero.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

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


/*
fun old() {

        /*

        val pieEntry1: PieEntry = PieEntry(
            10f,
            "test1",
        )

        val pieEntry2: PieEntry = PieEntry(
            30f,
            "test2",
        )

        val newList = mutableListOf<PieEntry>()

        newList.add(pieEntry1)
        newList.add(pieEntry2)

        val dataSet = PieDataSet(newList, "Example Label");
        val data = PieData(dataSet)

        pieChart.data = data
        pieChart.invalidate()

         */


        val pieChart = PieChart(LocalContext.current)

        // xvalues
        val xvalues = ArrayList<String >()
        xvalues.add("Coal")
        xvalues.add("Petrolium")
        xvalues.add("Natural Gas")
        xvalues.add("Renewable Energy")
        xvalues.add("Nuclear")


        // yvalues



        val piechartentry = ArrayList<PieEntry>()


        piechartentry.add( PieEntry(23.5f, 0 ))
        piechartentry.add( PieEntry(45.5f, 1 ))
        piechartentry.add( PieEntry(68.5f, 2 ))






        // fill the chart
        val piedataset = PieDataSet(piechartentry," Consumption")

        piedataset.color = Color.Red.toArgb()

        piedataset.sliceSpace = 3f
        val test = PieData(piedataset)
        //val data = PieData(xvalues, piedataset)

        pieChart.data = test


        pieChart.holeRadius = 5f
        pieChart.setBackgroundColor(Color.Gray.toArgb())

        pieChart.invalidate()


}

 */

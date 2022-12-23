package com.tradiebot.cythero.presentation.util.chart

import com.github.mikephil.charting.interfaces.datasets.IDataSet

object ChartValueFormatter {
	fun format(
		type: ChartValueFormatterType,
		dataSet: IDataSet<*>,
		position: Float
	): String {
		(return when (type) {
			ChartValueFormatterType.VALUE_POSITION -> {
				"${position.toInt() + 1}" +
					" ${dataSet.getEntryForIndex(position.toInt()).data}"
			}
			ChartValueFormatterType.VALUE -> {
				"${(dataSet.getEntryForIndex(position.toInt()).data)}"
			}
			ChartValueFormatterType.DEFAULT -> {
				throw IllegalStateException("Default should not be formatted")
			}
		})
	}
}

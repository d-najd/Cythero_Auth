package com.tradiebot.cythero.presentation.util.chart

enum class ChartValueFormatterType {
	/** formatted { value position } ex { Fender 2 } */
	VALUE_POSITION,
	/** formatted { value } ex { Fender } */
	VALUE,
	/** doesn't format the value */
	DEFAULT,
}

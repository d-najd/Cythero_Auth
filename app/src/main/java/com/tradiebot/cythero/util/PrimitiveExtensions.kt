package com.tradiebot.cythero.util

import java.math.RoundingMode
import java.text.DecimalFormat

fun Double.includeDecimals(decimals: Int): Double {
    if(decimals < 1) throw IllegalArgumentException("1 or more decimal spaces are required")
    val pattern = "#." + "#".repeat(decimals)
    val df = DecimalFormat(pattern)
    df.roundingMode = RoundingMode.HALF_UP
    return df.format(this).toDouble()
}


fun Float.includeDecimals(decimals: Int): Float {
    if(decimals < 1) throw IllegalArgumentException("1 or more decimal spaces are required")
    val pattern = "#." + "#".repeat(decimals)
    val df = DecimalFormat(pattern)
    df.roundingMode = RoundingMode.HALF_UP
    df.format(this)
    return df.format(this).toFloat()
}

package com.tradiebot.cythero.presentation.util

object ChartsHelper {
    enum class GradeToColor(val rgb: String, val shortName: String, val longName: String){
        A("#00A83D", "A", "High"),
        B("#0078AA", "B", "Medium"),
        C("#D3212C", "C", "Low"),
        DEFAULT("#FF00FE", "DEF", "Default")
    }
}

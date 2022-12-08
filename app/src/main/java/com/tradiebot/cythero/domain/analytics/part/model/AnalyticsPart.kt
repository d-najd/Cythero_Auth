package com.tradiebot.cythero.domain.analytics.part.model

import com.google.gson.annotations.SerializedName
import com.tradiebot.cythero.domain.analytics.PartsEnum
import com.tradiebot.cythero.domain.analytics.GradesEnum

data class AnalyticsPart (
    @SerializedName("Overall Grade") val overallGrade: List<String>,
    @SerializedName("Overall Time") val overallTime: List<Double>,
    @SerializedName("Overall Time Sum") val overallTimeSum: Double,
    @SerializedName("Overall Paint Used") val overallPaintUsed: List<Double>,
    @SerializedName("Overall Coverage Improvement") val overallCoverageImprovement: Double,
    @SerializedName("Average Grade Overall") val averageGradeOverall: GradesEnum,
    @SerializedName("Average Grade Base") val averageGradeBase: GradesEnum,
    @SerializedName("Average Grade Clear") val averageGradeClear: GradesEnum,
    @SerializedName("Average Grade Primer") val averageGradePrimer: GradesEnum,
    @SerializedName("Average Coverage Overall") val averageCoverageOverall: Double,
    @SerializedName("Average Coverage Overall (Base)") val averageCoverageOverallBase: Double,
    @SerializedName("Average Coverage Overall (Clear)") val averageCoverageOverallClear: Double,
    @SerializedName("Average Coverage Overall (Primer)") val averageCoverageOverallPrimer: Double,
    @SerializedName("Average High Coverage (Base)") val averageHighCoverageBase: Double,
    @SerializedName("Average High Coverage (Clear)") val averageHighCoverageClear: Double,
    @SerializedName("Average High Coverage (Primer)") val averageHighCoveragePrimer: Double,
    @SerializedName("Average Good Coverage (Base)") val averageGoodCoverageBase: Double,
    @SerializedName("Average Good Coverage (Clear)") val averageGoodCoverageClear: Double,
    @SerializedName("Average Good Coverage (Primer)") val averageGoodCoveragePrimer: Double,
    @SerializedName("Average Low Coverage (Base)") val averageLowCoverageBase: Double,
    @SerializedName("Average Low Coverage (Clear)") val averageLowCoverageClear: Double,
    @SerializedName("Average Low Coverage (Primer)") val averageLowCoveragePrimer: Double,
    @SerializedName("Base Grade") val baseGrade: List<GradesEnum>,
    @SerializedName("Base Time") val baseTime: List<Double>,
    @SerializedName("Base Paint Used") val basePaintUsed: List<Double>,
    @SerializedName("Base Coverage Improvement") val baseCoverageImprovement: Double,
    @SerializedName("Base High Coverage") val baseHighCoverage: List<Double>,
    @SerializedName("Base Good Coverage") val baseGoodCoverage: List<Double>,
    @SerializedName("Base Low Coverage") val baseLowCoverage: List<Double>,
    @SerializedName("Clear Grade") val clearGrade: List<GradesEnum>,
    @SerializedName("Clear Time") val clearTime: List<Double>,
    @SerializedName("Clear Paint Used") val clearPaintUsed: List<Double>,
    @SerializedName("Clear Coverage Improvement") val clearCoverageImprovement: Double,
    @SerializedName("Clear High Coverage") val clearHighCoverage: List<Double>,
    @SerializedName("Clear Good Coverage") val clearGoodCoverage: List<Double>,
    @SerializedName("Clear Low Coverage") val clearLowCoverage: List<Double>,
    @SerializedName("Primer Coverage Improvement") val primerCoverageImprovement: Double,
    @SerializedName("Primer Good Coverage") val primerGoodCoverage: List<Double>,
    @SerializedName("Primer Grade") val primerGrade: List<GradesEnum>,
    @SerializedName("Primer High Coverage") val primerHighCoverage: List<Double>,
    @SerializedName("Primer Low Coverage") val primerLowCoverage: List<Double>,
    @SerializedName("Primer Paint Used") val primerPaintUsed: List<Double>,
    @SerializedName("Primer Time") val primerTime: List<Double>,
    @SerializedName("Total Base Time") val totalBaseTime: Double,
    @SerializedName("Total Clear Time") val totalClearTime: Double,
    @SerializedName("Total Primer Time") val totalPrimerTime: Double,
    @SerializedName("Paint Used Overall Sum") val paintUsedOverallSum: Double,
    @SerializedName("Paint Used Base") val paintUsedBase: Double,
    @SerializedName("Paint Used Clear") val paintUsedClear: Double,
    @SerializedName("Paint Used Primer") val paintUsedPrimer: Double,
    @SerializedName("Session End") val sessionEnd: List<String>,
    @SerializedName("Session Start") val sessionStart: List<String>,
    @SerializedName("Times Played") val timesPlayed: Int,
    @SerializedName("Parts") val parts: List<PartsEnum>,
    @SerializedName("User ID") val userID: List<Int>,
) {
    /*
    companion object {
        fun mockInstance(): List<AnalyticsPart> {
            return Gson().fromJson(MOCK_PARTS_ANALYTICS, AnalyticsParts::class.java).AnalyticsParts
        }
    }
     */
}


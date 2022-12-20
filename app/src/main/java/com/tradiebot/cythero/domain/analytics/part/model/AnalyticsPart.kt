package com.tradiebot.cythero.domain.analytics.part.model

import com.google.gson.annotations.SerializedName
import com.tradiebot.cythero.domain.analytics.Grade
import com.tradiebot.cythero.domain.analytics.Part
import java.io.Serializable
import java.util.*

data class AnalyticsPart (
    @SerializedName("Part") val part: Part,
    @SerializedName("Overall Grade") val overallGrade: List<Grade>,
    @SerializedName("Overall Time") val overallTime: List<Int>,
    @SerializedName("Overall Time Sum") val overallTimeSum: Int,
    @SerializedName("Overall Paint Used") val overallPaintUsed: List<Double>,
    @SerializedName("Overall Coverage Improvement") val overallCoverageImprovement: Double,
    @SerializedName("Average Grade Overall") val averageGradeOverall: Grade,
    @SerializedName("Average Grade Base") val averageGradeBase: Grade,
    @SerializedName("Average Grade Clear") val averageGradeClear: Grade,
    @SerializedName("Average Grade Primer") val averageGradePrimer: Grade,
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
    @SerializedName("Base Grade") val baseGrade: List<Grade>,
    @SerializedName("Base Time") val baseTime: List<Int>,
    @SerializedName("Base Paint Used") val basePaintUsed: List<Double>,
    @SerializedName("Base Coverage Improvement") val baseCoverageImprovement: Double,
    @SerializedName("Base High Coverage") val baseHighCoverage: List<Double>,
    @SerializedName("Base Good Coverage") val baseGoodCoverage: List<Double>,
    @SerializedName("Base Low Coverage") val baseLowCoverage: List<Double>,
    @SerializedName("Clear Grade") val clearGrade: List<Grade>,
    @SerializedName("Clear Time") val clearTime: List<Int>,
    @SerializedName("Clear Paint Used") val clearPaintUsed: List<Double>,
    @SerializedName("Clear Coverage Improvement") val clearCoverageImprovement: Double,
    @SerializedName("Clear High Coverage") val clearHighCoverage: List<Double>,
    @SerializedName("Clear Good Coverage") val clearGoodCoverage: List<Double>,
    @SerializedName("Clear Low Coverage") val clearLowCoverage: List<Double>,
    @SerializedName("Primer Coverage Improvement") val primerCoverageImprovement: Double,
    @SerializedName("Primer Good Coverage") val primerGoodCoverage: List<Double>,
    @SerializedName("Primer Grade") val primerGrade: List<Grade>,
    @SerializedName("Primer High Coverage") val primerHighCoverage: List<Double>,
    @SerializedName("Primer Low Coverage") val primerLowCoverage: List<Double>,
    @SerializedName("Primer Paint Used") val primerPaintUsed: List<Double>,
    @SerializedName("Primer Time") val primerTime: List<Int>,
    @SerializedName("Total Base Time") val totalBaseTime: Int,
    @SerializedName("Total Clear Time") val totalClearTime: Int,
    @SerializedName("Total Primer Time") val totalPrimerTime: Int,
    @SerializedName("Paint Used Overall Sum") val paintUsedOverallSum: Double,
    @SerializedName("Paint Used Base") val paintUsedBase: Double,
    @SerializedName("Paint Used Clear") val paintUsedClear: Double,
    @SerializedName("Paint Used Primer") val paintUsedPrimer: Double,
    @SerializedName("Session Start") val sessionStart: List<Date>,
    @SerializedName("Session End") val sessionEnd: List<Date>,
    @SerializedName("Times Played") val timesPlayed: Int,
    @SerializedName("Parts") val parts: List<Part>,
    @SerializedName("User ID") val userIDs: List<Int>,
): Serializable {
    /*
    companion object {
        fun mockInstance(): List<AnalyticsPart> {
            return Injekt.get<Gson>().fromJson(MOCK_PARTS_ANALYTICS, AnalyticsParts::class.java).AnalyticsParts
        }
    }
     */
}


package com.tradiebot.cythero.domain.analytics.part.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AnalyticsParts(
    @SerializedName("Parts") val AnalyticsParts: List<AnalyticsPart>
){
    companion object {
        fun mockInstance(): AnalyticsParts {
            return Gson().fromJson(MOCK_PARTS_ANALYTICS, AnalyticsParts::class.java)
        }
    }
}

data class AnalyticsPart (
    @SerializedName("Overall Grade") val overallGrade: List<String>,
    @SerializedName("Overall Time") val overallTime: List<Double>,
    @SerializedName("Overall Time Sum") val overallTimeSum: Double,
    @SerializedName("Overall Paint Used") val overallPaintUsed: List<Double>,
    @SerializedName("Overall Coverage Improvement") val overallCoverageImprovement: Double,
    @SerializedName("Average Grade Overall") val averageGradeOverall: String,
    @SerializedName("Average Grade Base") val averageGradeBase: String,
    @SerializedName("Average Grade Clear") val averageGradeClear: String,
    @SerializedName("Average Grade Primer") val averageGradePrimer: String,
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
    @SerializedName("Base Grade") val baseGrade: List<String>,
    @SerializedName("Base Time") val baseTime: List<Double>,
    @SerializedName("Base Paint Used") val basePaintUsed: List<Double>,
    @SerializedName("Base Coverage Improvement") val baseCoverageImprovement: Double,
    @SerializedName("Base High Coverage") val baseHighCoverage: List<Double>,
    @SerializedName("Base Good Coverage") val baseGoodCoverage: List<Double>,
    @SerializedName("Base Low Coverage") val baseLowCoverage: List<Double>,
    @SerializedName("Clear Grade") val clearGrade: List<String>,
    @SerializedName("Clear Time") val clearTime: List<Double>,
    @SerializedName("Clear Paint Used") val clearPaintUsed: List<Double>,
    @SerializedName("Clear Coverage Improvement") val clearCoverageImprovement: Double,
    @SerializedName("Clear High Coverage") val clearHighCoverage: List<Double>,
    @SerializedName("Clear Good Coverage") val clearGoodCoverage: List<Double>,
    @SerializedName("Clear Low Coverage") val clearLowCoverage: List<Double>,
    @SerializedName("Primer Coverage Improvement") val primerCoverageImprovement: Double,
    @SerializedName("Primer Good Coverage") val primerGoodCoverage: List<Double>,
    @SerializedName("Primer Grade") val primerGrade: List<String>,
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
    @SerializedName("Parts") val parts: List<String>,
    @SerializedName("User ID") val userID: List<Int>,
        ): Serializable {
            companion object {
                fun mockInstance(): List<AnalyticsPart> {
                    return Gson().fromJson(MOCK_PARTS_ANALYTICS, AnalyticsParts::class.java).AnalyticsParts
                }
            }
        }

private const val MOCK_PARTS_ANALYTICS =
        "{\n" +
        "    \"Parts\": [\n" +
        "        {\n" +
        "            \"Average Coverage Overall\": 28.40531640253969,\n" +
        "            \"Average Coverage Overall (Base)\": 27.268331999999994,\n" +
        "            \"Average Coverage Overall (Clear)\": 29.000893719047614,\n" +
        "            \"Average Coverage Overall (Primer)\": 28.946723488571436,\n" +
        "            \"Average Good Coverage (Base)\": 56.41146714285714,\n" +
        "            \"Average Good Coverage (Clear)\": 56.46420714285714,\n" +
        "            \"Average Good Coverage (Primer)\": 43.225814285714286,\n" +
        "            \"Average Grade Base\": \"B\",\n" +
        "            \"Average Grade Clear\": \"B\",\n" +
        "            \"Average Grade Overall\": \"B\",\n" +
        "            \"Average Grade Primer\": \"C\",\n" +
        "            \"Average High Coverage (Base)\": 12.370036857142855,\n" +
        "            \"Average High Coverage (Clear)\": 0.05660644285714286,\n" +
        "            \"Average High Coverage (Primer)\": 33.453238514285715,\n" +
        "            \"Average Low Coverage (Base)\": 13.023492,\n" +
        "            \"Average Low Coverage (Clear)\": 30.48186757142857,\n" +
        "            \"Average Low Coverage (Primer)\": 10.161117665714286,\n" +
        "            \"Base Coverage Improvement\": 27.268331999999994,\n" +
        "            \"Base Good Coverage\": [\n" +
        "                31.45199,\n" +
        "                88.43163,\n" +
        "                88.43163,\n" +
        "                48.47793,\n" +
        "                48.47793,\n" +
        "                44.80458,\n" +
        "                44.80458\n" +
        "            ],\n" +
        "            \"Base Grade\": [\n" +
        "                \"C\",\n" +
        "                \"A\",\n" +
        "                \"A\",\n" +
        "                \"C\",\n" +
        "                \"C\",\n" +
        "                \"C\",\n" +
        "                \"C\"\n" +
        "            ],\n" +
        "            \"Base High Coverage\": [\n" +
        "                68.54859,\n" +
        "                6.279686,\n" +
        "                6.279686,\n" +
        "                0.0,\n" +
        "                0.0,\n" +
        "                2.741148,\n" +
        "                2.741148\n" +
        "            ],\n" +
        "            \"Base Low Coverage\": [\n" +
        "                0.0,\n" +
        "                3.033962,\n" +
        "                3.033962,\n" +
        "                24.03654,\n" +
        "                24.03654,\n" +
        "                18.51172,\n" +
        "                18.51172\n" +
        "            ],\n" +
        "            \"Base Paint Used\": [\n" +
        "                68.26933,\n" +
        "                32.8765,\n" +
        "                32.8765,\n" +
        "                6.656877,\n" +
        "                6.656877,\n" +
        "                9.258784,\n" +
        "                9.258784\n" +
        "            ],\n" +
        "            \"Base Time\": [\n" +
        "                32.0,\n" +
        "                15.0,\n" +
        "                15.0,\n" +
        "                5.0,\n" +
        "                5.0,\n" +
        "                5.0,\n" +
        "                5.0\n" +
        "            ],\n" +
        "            \"Clear Coverage Improvement\": 29.000893719047614,\n" +
        "            \"Clear Good Coverage\": [\n" +
        "                97.06773,\n" +
        "                65.90218,\n" +
        "                65.90218,\n" +
        "                48.23163,\n" +
        "                48.23163,\n" +
        "                34.95705,\n" +
        "                34.95705\n" +
        "            ],\n" +
        "            \"Clear Grade\": [\n" +
        "                \"A\",\n" +
        "                \"A\",\n" +
        "                \"A\",\n" +
        "                \"B\",\n" +
        "                \"B\",\n" +
        "                \"C\",\n" +
        "                \"C\"\n" +
        "            ],\n" +
        "            \"Clear High Coverage\": [\n" +
        "                0.3962451,\n" +
        "                0.0,\n" +
        "                0.0,\n" +
        "                0.0,\n" +
        "                0.0,\n" +
        "                0.0,\n" +
        "                0.0\n" +
        "            ],\n" +
        "            \"Clear Low Coverage\": [\n" +
        "                2.536613,\n" +
        "                26.49054,\n" +
        "                26.49054,\n" +
        "                36.9665,\n" +
        "                36.9665,\n" +
        "                41.96119,\n" +
        "                41.96119\n" +
        "            ],\n" +
        "            \"Clear Paint Used\": [\n" +
        "                29.11287,\n" +
        "                8.353868,\n" +
        "                8.353868,\n" +
        "                8.839866,\n" +
        "                8.839866,\n" +
        "                6.649091,\n" +
        "                6.649091\n" +
        "            ],\n" +
        "            \"Clear Time\": [\n" +
        "                14.0,\n" +
        "                5.0,\n" +
        "                5.0,\n" +
        "                5.0,\n" +
        "                5.0,\n" +
        "                5.0,\n" +
        "                5.0\n" +
        "            ],\n" +
        "            \"Overall Coverage Improvement\": 28.405316402539682,\n" +
        "            \"Overall Grade\": [\n" +
        "                \"B\",\n" +
        "                \"B\",\n" +
        "                \"B\",\n" +
        "                \"C\",\n" +
        "                \"C\",\n" +
        "                \"C\",\n" +
        "                \"C\"\n" +
        "            ],\n" +
        "            \"Overall Paint Used\": [\n" +
        "                157.034,\n" +
        "                49.964,\n" +
        "                49.964,\n" +
        "                25.987,\n" +
        "                25.987,\n" +
        "                27.403,\n" +
        "                27.403\n" +
        "            ],\n" +
        "            \"Overall Time\": [\n" +
        "                78.0,\n" +
        "                38.0,\n" +
        "                38.0,\n" +
        "                29.0,\n" +
        "                29.0,\n" +
        "                23.0,\n" +
        "                23.0\n" +
        "            ],\n" +
        "            \"Overall Time Sum\": 258.0,\n" +
        "            \"Paint Used Base\": 165.854,\n" +
        "            \"Paint Used Clear\": 76.799,\n" +
        "            \"Paint Used Overall Sum\": 363.741,\n" +
        "            \"Paint Used Primer\": 121.088,\n" +
        "            \"Part\": \"Fender\",\n" +
        "            \"Parts\": [\n" +
        "                \"Fender\",\n" +
        "                \"Fender\",\n" +
        "                \"Fender\",\n" +
        "                \"Fender\",\n" +
        "                \"Fender\",\n" +
        "                \"Fender\",\n" +
        "                \"Fender\"\n" +
        "            ],\n" +
        "            \"Primer Coverage Improvement\": 28.946723488571436,\n" +
        "            \"Primer Good Coverage\": [\n" +
        "                67.47278,\n" +
        "                61.9043,\n" +
        "                61.9043,\n" +
        "                0.0,\n" +
        "                0.0,\n" +
        "                55.64966,\n" +
        "                55.64966\n" +
        "            ],\n" +
        "            \"Primer Grade\": [\n" +
        "                \"C\",\n" +
        "                \"C\",\n" +
        "                \"C\",\n" +
        "                \"C\",\n" +
        "                \"C\",\n" +
        "                \"C\",\n" +
        "                \"C\"\n" +
        "            ],\n" +
        "            \"Primer High Coverage\": [\n" +
        "                32.44409,\n" +
        "                0.0,\n" +
        "                0.0,\n" +
        "                100.0,\n" +
        "                100.0,\n" +
        "                0.8642898,\n" +
        "                0.8642898\n" +
        "            ],\n" +
        "            \"Primer Low Coverage\": [\n" +
        "                0.08372366,\n" +
        "                13.16057,\n" +
        "                13.16057,\n" +
        "                0.0,\n" +
        "                0.0,\n" +
        "                22.36148,\n" +
        "                22.36148\n" +
        "            ],\n" +
        "            \"Primer Paint Used\": [\n" +
        "                59.65214,\n" +
        "                8.733523,\n" +
        "                8.733523,\n" +
        "                10.48994,\n" +
        "                10.48994,\n" +
        "                11.49467,\n" +
        "                11.49467\n" +
        "            ],\n" +
        "            \"Primer Time\": [\n" +
        "                32.0,\n" +
        "                18.0,\n" +
        "                18.0,\n" +
        "                19.0,\n" +
        "                19.0,\n" +
        "                13.0,\n" +
        "                13.0\n" +
        "            ],\n" +
        "            \"Session End\": [\n" +
        "                \"2022-09-06 14:48:00\",\n" +
        "                \"2022-09-15 11:35:08\",\n" +
        "                \"2022-09-15 11:35:08\",\n" +
        "                \"2022-09-15 11:36:47\",\n" +
        "                \"2022-09-15 11:36:47\",\n" +
        "                \"2022-09-15 11:53:46\",\n" +
        "                \"2022-09-15 11:53:46\"\n" +
        "            ],\n" +
        "            \"Session Start\": [\n" +
        "                \"2022-09-06 14:46:38\",\n" +
        "                \"2022-09-15 11:34:27\",\n" +
        "                \"2022-09-15 11:34:27\",\n" +
        "                \"2022-09-15 11:36:14\",\n" +
        "                \"2022-09-15 11:36:14\",\n" +
        "                \"2022-09-15 11:53:21\",\n" +
        "                \"2022-09-15 11:53:21\"\n" +
        "            ],\n" +
        "            \"Times Played\": 7,\n" +
        "            \"Total Base Time\": 82.0,\n" +
        "            \"Total Clear Time\": 44.0,\n" +
        "            \"Total Primer Time\": 132.0,\n" +
        "            \"User ID\": [\n" +
        "                3,\n" +
        "                3,\n" +
        "                3,\n" +
        "                3,\n" +
        "                3,\n" +
        "                3,\n" +
        "                3\n" +
        "            ]\n" +
        "        }\n" +
        "    ]\n" +
        "}"
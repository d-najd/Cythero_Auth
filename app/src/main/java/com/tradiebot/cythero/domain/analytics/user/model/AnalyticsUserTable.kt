package com.tradiebot.cythero.domain.analytics.user.model

import com.google.gson.annotations.SerializedName
import com.tradiebot.cythero.domain.analytics.Grade
import com.tradiebot.cythero.domain.analytics.Part

/**
 *
 * @param totalTimeSpentMin time played in minutes use [totalTimePlayedSec] for more accuracy
 * @param totalTimePlayedSec time played in seconds
 */
data class AnalyticsUserTable(
    @SerializedName("Base Good Coverage") val baseGoodCoverage: List<Double>,
    @SerializedName("Base High Coverage") val baseHighCoverage: List<Double>,
    @SerializedName("Base Low Coverage") val baseLowCoverage: List<Double>,
    @SerializedName("Clear Coat Used (Milliliters)") val clearCoatUsedMilliliters: List<Double>,
    @SerializedName("Clear Good Coverage") val clearGoodCoverage: List<Double>,
    @SerializedName("Clear High Coverage") val clearHighCoverage: List<Double>,
    @SerializedName("Clear Low Coverage") val clearLowCoverage: List<Double>,
    @SerializedName("Color Used (Milliliters)") val colorUsedMilliliters: List<Double>,
    @SerializedName("Grade") val grade: List<Grade>,
    @SerializedName("Part") val part: List<Part>,
    @SerializedName("Primer Good Coverage") val primerGoodCoverage: List<Double>,
    @SerializedName("Primer High Coverage") val primerHighCoverage: List<Double>,
    @SerializedName("Primer Low Coverage") val primerLowCoverage: List<Double>,
    @SerializedName("Primer Used (Milliliters)") val primerUsedMilliliters: List<Double>,
    @SerializedName("Session ID") val sessionID: List<String>,
    @SerializedName("Total Paint Used") val totalPaintUsed: List<Double>,
    @SerializedName("Total Paint Used (Milliliters)") val totalPaintUsedMilliliters: List<Double>,
    @SerializedName("Total Time Played") val totalTimePlayedSec: List<Double>,
    @SerializedName("Total Time Spent") val totalTimeSpentMin: List<Double>,
    @SerializedName("User ID") val userIDs: List<Long>,
)
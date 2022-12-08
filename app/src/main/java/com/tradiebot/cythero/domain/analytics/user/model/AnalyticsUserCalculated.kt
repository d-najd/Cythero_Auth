package com.tradiebot.cythero.domain.analytics.user.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AnalyticsUserCalculated(
    @SerializedName("Average Paint") val averagePaint: Double,
    @SerializedName("Average Time") val averageTime: Double,
    @SerializedName("Least Paint") val leastPaint: Double,
    @SerializedName("Longest Time") val longestTime: Double,
    @SerializedName("Most Paint") val mostPaint: Double,
    @SerializedName("Shortest Time") val shortestTime: Float,
    @SerializedName("User IDs") val userIDs: List<Long>,
)
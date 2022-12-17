package com.tradiebot.cythero.domain.analytics.usage.model


import com.google.gson.annotations.SerializedName

data class AnalyticsUsageLabelsHolder(
    @SerializedName("analytics") val analytics: List<AnalyticsUsageLabels>
)

data class AnalyticsUsageLabels(
    @SerializedName("application_id") val applicationId: Int,
    @SerializedName("date") val date: String,
    @SerializedName("id") val id: Int,
    @SerializedName("label_id") val labelId: Int,
    @SerializedName("score") val score: String,
    @SerializedName("session_id") val sessionId: String,
    @SerializedName("user_id") val userId: Int
)

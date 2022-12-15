package com.tradiebot.cythero.domain.analytics.usage.model

import com.google.gson.annotations.SerializedName
import com.tradiebot.cythero.util.CytheroDateFormat
import com.tradiebot.cythero.util.mAppContext
import java.util.*

/**
 * Analytics that are much easier to sort than [AnalyticsUsage] because in [AnalyticsUsage] the
 * structure is single object which contains lists as fields, in [AnalyticsUsageSortable] it is
 * list of objects which contain objects as fields instead.
 * The date is being converted to [CytheroDateFormat.defaultRequestDateFormat] and the part to
 * string to improve performance
 */
data class AnalyticsUsageSortable(
	@SerializedName("Date") val date: String,
	@SerializedName("Paint Used (Ml)") val paintUsedMl: Double,
	@SerializedName("Part") val part: String,
	@SerializedName("Session ID") val sessionID: String,
	@SerializedName("Total Time Spent (min)") val totalTimeSpentMin: Double,
	@SerializedName("User") val user: String
)

fun AnalyticsUsage.toAnalyticsSortable(): List<AnalyticsUsageSortable> = List(this.sessionID.size) { i ->
	AnalyticsUsageSortable(
		date = CytheroDateFormat.defaultRequestDateFormat().format(this.date[i]),
		paintUsedMl = this.paintUsedMl[i],
		part = mAppContext().getString(this.part[i].nameId),
		sessionID = this.sessionID[i],
		totalTimeSpentMin = this.totalTimeSpentMin[i],
		user = this.user[i],
	)
}

fun List<AnalyticsUsageSortable>.sortByType(
	type: AnalyticsUsageSortType,
	reverse: Boolean = false,
): List<AnalyticsUsageSortable> {
	sortedBy {
		when (type) {
			AnalyticsUsageSortType.DATE -> it.date
			AnalyticsUsageSortType.PAINT_USED -> it.paintUsedMl.toString()
			AnalyticsUsageSortType.PART -> it.part
			AnalyticsUsageSortType.SESSION_ID -> it.sessionID
			AnalyticsUsageSortType.TOTAL_TIME_SPENT -> it.totalTimeSpentMin.toString()
			AnalyticsUsageSortType.USER -> it.user
		}
	}
	return if (reverse) reversed() else this
}

enum class AnalyticsUsageSortType {
	DATE,
	PAINT_USED,
	PART,
	SESSION_ID,
	TOTAL_TIME_SPENT,
	USER,
}

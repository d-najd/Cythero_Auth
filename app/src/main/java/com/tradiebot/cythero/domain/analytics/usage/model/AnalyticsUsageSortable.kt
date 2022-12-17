package com.tradiebot.cythero.domain.analytics.usage.model

import com.tradiebot.cythero.domain.analytics.Part
import com.tradiebot.cythero.util.mAppContext
import java.util.*


/**
 * Analytics that are much easier to sort than [AnalyticsUsage] because in [AnalyticsUsage] the
 * structure is single object which contains lists as fields, in [AnalyticsUsageSortableHolder] it is
 * list of objects which contain objects as fields instead.
 */
data class AnalyticsUsageSortable(
	val analyticsList: List<AnalyticsUsageSortableHolder>,
	val sortType: AnalyticsUsageSortType,
	val reverse: Boolean
)

data class AnalyticsUsageSortableHolder(
	val date: Date,
	val paintUsedMl: Double,
	val part: Part,
	val sessionID: String,
	val totalTimeSpentMin: Double,
	val user: String
)

fun AnalyticsUsage.toAnalyticsSortable() = AnalyticsUsageSortable(
	analyticsList = this.toAnalyticsSortableHolder(),
	sortType = AnalyticsUsageSortType.USER,
	reverse = true,
)

fun AnalyticsUsage.toAnalyticsSortableHolder() = List(this.sessionID.size) { i ->
	AnalyticsUsageSortableHolder(
		date = this.date[i],
		paintUsedMl = this.paintUsedMl[i],
		part = this.part[i],
		sessionID = this.sessionID[i],
		totalTimeSpentMin = this.totalTimeSpentMin[i],
		user = this.user[i],
	)
}

fun AnalyticsUsageSortable.sortByType(
	type: AnalyticsUsageSortType,
	reverse: Boolean = false,
): AnalyticsUsageSortable {
	val analyticsSorted = when(type){
		AnalyticsUsageSortType.DATE -> if(!reverse) analyticsList.sortedBy { it.date }
			else analyticsList.sortedByDescending { it.date }
		AnalyticsUsageSortType.PAINT_USED -> if(!reverse) analyticsList.sortedBy { it.paintUsedMl }
			else analyticsList.sortedByDescending { it.paintUsedMl }
		AnalyticsUsageSortType.PART -> if(!reverse) analyticsList.sortedBy { mAppContext().getString(it.part.nameId) }
			else analyticsList.sortedByDescending { mAppContext().getString(it.part.nameId) }
		AnalyticsUsageSortType.SESSION_ID -> if(!reverse) analyticsList.sortedBy { it.sessionID }
			else analyticsList.sortedByDescending { it.sessionID }
		AnalyticsUsageSortType.TOTAL_TIME_SPENT -> if(!reverse) analyticsList.sortedBy { it.totalTimeSpentMin }
			else analyticsList.sortedByDescending { it.totalTimeSpentMin }
		AnalyticsUsageSortType.USER -> if(!reverse) analyticsList.sortedBy { it.user }
			else analyticsList.sortedByDescending { it.user }
	}
	
	return AnalyticsUsageSortable(
		analyticsList = analyticsSorted,
		sortType = type,
		reverse = reverse
	)
}

enum class AnalyticsUsageSortType {
	DATE,
	PAINT_USED,
	PART,
	SESSION_ID,
	TOTAL_TIME_SPENT,
	USER,
}

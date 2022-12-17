package com.tradiebot.cythero.domain.analytics.usage.model

import com.tradiebot.cythero.domain.analytics.Part
import com.tradiebot.cythero.util.mAppContext
import java.util.*


data class AnalyticsUsageSortableHolder(
	val analyticsList: List<AnalyticsUsageSortable>,
	val sortType: AnalyticsUsageSortType,
	val reverse: Boolean
)

/**
 * Analytics that are much easier to sort than [AnalyticsUpdate] because in [AnalyticsUpdate] the
 * structure is single object which contains lists as fields, in [AnalyticsUsageSortable] it is
 * list of objects which contain objects as fields instead.
 */
data class AnalyticsUsageSortable(
	val date: Date,
	val paintUsedMl: Double,
	val part: Part,
	val sessionID: String,
	val totalTimeSpentMin: Double,
	val user: String
)

fun AnalyticsUpdate.toAnalyticsSortable() = AnalyticsUsageSortableHolder(
	analyticsList = this.toAnalyticsSortableHolder(),
	sortType = AnalyticsUsageSortType.USER,
	reverse = true,
)

fun AnalyticsUpdate.toAnalyticsSortableHolder() = List(this.sessionID.size) { i ->
	AnalyticsUsageSortable(
		date = this.date[i],
		paintUsedMl = this.paintUsedMl[i],
		part = this.part[i],
		sessionID = this.sessionID[i],
		totalTimeSpentMin = this.totalTimeSpentMin[i],
		user = this.user[i],
	)
}

fun AnalyticsUsageSortableHolder.sortByType(
	type: AnalyticsUsageSortType,
	reverse: Boolean = false,
): AnalyticsUsageSortableHolder {
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
	
	return AnalyticsUsageSortableHolder(
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

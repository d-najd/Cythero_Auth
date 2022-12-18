package com.tradiebot.cythero.domain.analytics.usage.interactor

import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUsageLabel
import com.tradiebot.cythero.domain.analytics.usage.service.AnalyticsUsageService
import com.tradiebot.cythero.domain.auth.model.Auth

class RequestUsageAnalyticsLabels(
	private val analyticsUsageService: AnalyticsUsageService,
) {
	
	suspend fun await(userAuth: Auth): List<AnalyticsUsageLabel> {
		return analyticsUsageService.getLabels(userAuth)
	}
	
}

package com.tradiebot.cythero.domain.analytics.shared.interactor

import com.tradiebot.cythero.domain.analytics.shared.model.AnalyticsLabel
import com.tradiebot.cythero.domain.analytics.shared.service.AnalyticsService
import com.tradiebot.cythero.domain.auth.model.Auth

class RequestAnalyticsLabels(
	private val analyticsService: AnalyticsService,
) {
	
	suspend fun await(userAuth: Auth): List<AnalyticsLabel> {
		return analyticsService.getLabels(userAuth)
	}
	
}

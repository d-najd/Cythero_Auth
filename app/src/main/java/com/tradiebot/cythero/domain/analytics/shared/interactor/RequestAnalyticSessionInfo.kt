package com.tradiebot.cythero.domain.analytics.shared.interactor

import com.tradiebot.cythero.domain.analytics.shared.model.AnalyticSession
import com.tradiebot.cythero.domain.analytics.shared.service.AnalyticsService
import com.tradiebot.cythero.domain.auth.model.Auth

class RequestAnalyticSessionInfo(
	private val analyticsService: AnalyticsService,
){
	
	suspend fun await(
		userAuth: Auth,
		sessionID: String,
	): List<AnalyticSession> {
		return analyticsService.getSessionInfo(
			userAuth = userAuth,
			sessionID = sessionID
		)
	}
	
}
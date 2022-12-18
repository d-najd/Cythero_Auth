package com.tradiebot.cythero.network.analytics.shared

import com.google.gson.Gson
import com.tradiebot.cythero.domain.analytics.shared.model.AnalyticSession
import com.tradiebot.cythero.domain.analytics.shared.model.AnalyticsLabel
import com.tradiebot.cythero.domain.analytics.shared.model.AnalyticsLabelsHolder
import com.tradiebot.cythero.domain.analytics.shared.model.AnalyticsSessionHolder
import com.tradiebot.cythero.domain.analytics.shared.service.AnalyticsService
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.network.utils.*
import okhttp3.OkHttpClient
import okhttp3.Response
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import java.io.IOException


object AnalyticsServiceImpl: AnalyticsService {
    private val client = Injekt.get<OkHttpClient>()
    private val gson = Injekt.get<Gson>()
    
    override suspend fun getLabels(userAuth: Auth): List<AnalyticsLabel> {
        val request = GET(
            url = Urls.ANALYTICS_LABELS,
            headers = HeadersBuilder().addBearerToken(userAuth).build(),
        )
        
        try {
            val response: Response = client.newCall(request).execute().printResponse()
            
            response.takeIf { res -> res.isSuccessful }.let {
                // Yes it is possible to do this in one line and yes it does crash the app
                val temp = gson.fromJson(it!!.body.string(), AnalyticsLabelsHolder::class.java)
                
                return temp.analyticsLabels
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return emptyList()
    }
    
    override suspend fun getSessionInfo(userAuth: Auth, sessionID: String): List<AnalyticSession> {
        val request = GET(
            url = Urls.ANALYTICS_ANALYTICS + "?session_id=$sessionID",
            headers = HeadersBuilder().addBearerToken(userAuth).build(),
        )
        
        try {
            val response: Response = client.newCall(request).execute().printResponse()
            
            response.takeIf { res -> res.isSuccessful }.let {
                // Doing it this way because it may crash in 1 liner for some reason
                val temp = gson.fromJson(it!!.body.string(), AnalyticsSessionHolder::class.java)
                
                return temp.analyticSessions
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return emptyList()
    }
}



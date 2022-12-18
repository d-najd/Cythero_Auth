package com.tradiebot.cythero.network.analytics.usage

import com.google.gson.Gson
import com.tradiebot.cythero.domain.analytics.shared.model.AnalyticsLabel
import com.tradiebot.cythero.domain.analytics.shared.model.AnalyticsLabelsHolder
import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUsage
import com.tradiebot.cythero.domain.analytics.usage.model.AnalyticsUsageHolder
import com.tradiebot.cythero.domain.analytics.usage.service.AnalyticsUsageService
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.network.utils.*
import com.tradiebot.cythero.util.CytheroDateFormat
import okhttp3.OkHttpClient
import okhttp3.Response
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import java.io.IOException
import java.util.*

object AnalyticsUsageServiceImpl: AnalyticsUsageService {
    private val client = Injekt.get<OkHttpClient>()
    private val gson = Injekt.get<Gson>()
    
    override suspend fun getAnalytics(
        userAuth: Auth,
        userIDs: List<Long>,
        dateRange: Pair<Date, Date>
    ): AnalyticsUsage? {
        val dateFormat = CytheroDateFormat.defaultRequestDateFormat()
        
        val body = MultipartBodyBuilder()
            .addFormDataPart("user_ids", userIDs)
            .addFormDataPart("date_from", dateFormat.format(dateRange.first))
            .addFormDataPart("date_to", dateFormat.format(dateRange.second))
            .build()
        
        val request = POST(
            url = Urls.ANALYTICS_USAGE,
            body = body,
            headers = HeadersBuilder().addBearerToken(userAuth).build(),
        )
        
        try {
            val response: Response = client.newCall(request).execute().printResponse()
            
            response.takeIf { res -> res.isSuccessful }.let {
                // Yes it is possible to do this in one line and yes it does crash the app
                val temp = gson.fromJson(it!!.body.string(), AnalyticsUsageHolder::class.java)
    
                return temp.analyticsUsage
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}

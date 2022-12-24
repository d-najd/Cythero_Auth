package com.tradiebot.cythero.network.analytics.user

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tradiebot.cythero.domain.analytics.user.model.AnalyticsUser
import com.tradiebot.cythero.domain.analytics.user.service.AnalyticsUserService
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.network.utils.*
import com.tradiebot.cythero.util.CytheroDateFormat
import okhttp3.OkHttpClient
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import java.util.*

object AnalyticsUserServiceImpl: AnalyticsUserService {
    private val client = Injekt.get<OkHttpClient>()
    private val gson = Injekt.get<Gson>()

    override suspend fun getAnalytics(
        userAuth: Auth,
        userIDs: List<Long>,
        dateRange: Pair<Date, Date>
    ): Map<Long, AnalyticsUser> {
        val dateFormat = CytheroDateFormat.defaultRequestDateFormat()

        val body = MultipartBodyBuilder()
            .addFormDataPart("user_ids", userIDs)
            .addFormDataPart("date_from", dateFormat.format(dateRange.first))
            .addFormDataPart("date_to", dateFormat.format(dateRange.second))
            .build()

        val request = POST(
            url = Urls.ANALYTICS_USER_SPRAYVERSE,
            body = body,
            headers = HeadersBuilder().addBearerToken(userAuth).build(),
        )
    
        return client.newCall(request).processRequest<Map<Long, AnalyticsUser>?> {
            val analyticsUserType = object : TypeToken<Map<Long, AnalyticsUser>>(){}.type
            return@processRequest gson.fromJson(it.body.string(), analyticsUserType)
        }.orEmpty()
    }
}
package com.tradiebot.cythero.network.analytics.user

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tradiebot.cythero.domain.analytics.user.model.AnalyticsUser
import com.tradiebot.cythero.domain.analytics.user.service.AnalyticsUserService
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.network.utils.*
import com.tradiebot.cythero.util.CytheroDateFormat
import okhttp3.OkHttpClient
import okhttp3.Response
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import java.io.IOException
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

        try {
            val response: Response = client.newCall(request).execute().printResponse()
    
            response.takeIf { res -> res.isSuccessful }.let {
                val analyticsUserType = object : TypeToken<Map<Long, AnalyticsUser>>(){}.type
                return gson.fromJson(it!!.body.string(), analyticsUserType)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            when(e){
                is IOException,
                is NullPointerException -> { }
                else -> throw e
            }
        }
        return emptyMap()
    }
}
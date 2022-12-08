package com.tradiebot.cythero.network.analytics.user

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tradiebot.cythero.domain.analytics.user.model.AnalyticsUser
import com.tradiebot.cythero.domain.analytics.user.service.AnalyticsUserService
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.network.utils.*
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

object AnalyticsUserServiceImpl: AnalyticsUserService {
    private val client = OkHttpClient() // TODO replace with injekt
    private val gson = Gson() // TODO replace with injekt

    override suspend fun getAnalytics(
        userAuth: Auth,
        userIDs: List<Long>,
    ): Map<Long, AnalyticsUser> {
        val body = MultipartBodyBuilder()
            .addFormDataPart("user_ids", userIDs)
            .build()

        val request = POST(
            url = Urls.ANALYTICS_SPRAYVERSE,
            body = body,
            headers = HeadersBuilder().addBearerToken(userAuth).build(),
        )

        try {
            val response: Response = client.newCall(request).execute().printResponse()

            if (response.isSuccessful) {
                response.use {
                    val analyticsUserType = object : TypeToken<Map<Long, AnalyticsUser>>(){}.type
                    return gson.fromJson(response.body.string(), analyticsUserType)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return emptyMap()
    }
}
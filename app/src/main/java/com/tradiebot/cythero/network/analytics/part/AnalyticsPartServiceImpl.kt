package com.tradiebot.cythero.network.analytics.part

import com.google.gson.Gson
import com.tradiebot.cythero.app.util.view.ContextHolder
import com.tradiebot.cythero.domain.analytics.PartEnum
import com.tradiebot.cythero.domain.analytics.part.model.AnalyticsPart
import com.tradiebot.cythero.domain.analytics.part.model.AnalyticsParts
import com.tradiebot.cythero.domain.analytics.part.service.AnalyticsPartService
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.network.utils.*
import okhttp3.OkHttpClient
import okhttp3.Response
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import java.io.IOException

object AnalyticsPartServiceImpl: AnalyticsPartService {
    private val client = Injekt.get<OkHttpClient>()
    private val gson = Injekt.get<Gson>()

    override suspend fun getAnalytics(
        userAuth: Auth,
        userIDs: List<Long>,
        parts: List<PartEnum>
    ): List<AnalyticsPart> {
        val contextHolder = Injekt.get<ContextHolder>()

        val body = MultipartBodyBuilder()
            .addFormDataPart("users", userIDs)
            .addFormDataPart("parts", parts.map { part -> "\"${contextHolder.getString(part.nameId)}\"" })
            .build()

        val request = POST(
            url = Urls.ANALYTICS_PART_SPRAYVERSE,
            body = body,
            headers = HeadersBuilder().addBearerToken(userAuth).build(),
        )

        try {
            val response: Response = client.newCall(request).execute().printResponse()

            if (response.isSuccessful) {
                response.use {
                    return gson.fromJson(response.body.string(), AnalyticsParts::class.java).AnalyticsParts
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return emptyList()

    }
}
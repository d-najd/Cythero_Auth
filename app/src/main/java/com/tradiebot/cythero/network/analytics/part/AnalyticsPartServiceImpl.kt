package com.tradiebot.cythero.network.analytics.part

import com.google.gson.Gson
import com.tradiebot.cythero.domain.analytics.Part
import com.tradiebot.cythero.domain.analytics.part.model.AnalyticsPart
import com.tradiebot.cythero.domain.analytics.part.model.AnalyticsParts
import com.tradiebot.cythero.domain.analytics.part.service.AnalyticsPartService
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.network.utils.*
import com.tradiebot.cythero.util.mAppContext
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
        parts: List<Part>
    ): List<AnalyticsPart> {
        val body = MultipartBodyBuilder()
            .addFormDataPart("users", userIDs)
            .addFormDataPart("parts", parts.map { part -> "\"${mAppContext().getString(part.nameId)}\"" })
            .build()

        val request = POST(
            url = Urls.ANALYTICS_PART_SPRAYVERSE,
            body = body,
            headers = HeadersBuilder().addBearerToken(userAuth).build(),
        )

        try {
            val response: Response = client.newCall(request).execute().printResponse()
    
            response.takeIf { res -> res.isSuccessful }.let {
                return gson.fromJson(it!!.body.string(), AnalyticsParts::class.java).AnalyticsParts
            }
        } catch (e: Exception) {
            e.printStackTrace()
            when(e){
                is IOException,
                is NullPointerException -> { }
                else -> throw e
            }
        }
        return emptyList()
    }
}
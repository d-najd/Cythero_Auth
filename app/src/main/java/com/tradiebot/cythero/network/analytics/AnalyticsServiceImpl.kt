package com.tradiebot.cythero.network.analytics

import com.google.gson.Gson
import com.tradiebot.cythero.domain.analytics.model.Analytics
import com.tradiebot.cythero.domain.analytics.service.AnalyticsService
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.network.utils.POST
import com.tradiebot.cythero.network.utils.Urls
import com.tradiebot.cythero.network.utils.addBearerToken
import com.tradiebot.cythero.network.utils.printResponse
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

object AnalyticsServiceImpl: AnalyticsService {
    private val client = OkHttpClient() // TODO replace with injekt
    private val gson = Gson() // TODO replace with injekt

    override suspend fun getUserAnalytics(
        userAuth: Auth,
        userIDs: List<Long>
    ): List<Analytics> {

        val request = POST(
            url = Urls.ANALYTICS_SPRAYVERSE,
            headers = Headers.Builder().addBearerToken(userAuth).build(),
        )

        try {
            val response: Response = client.newCall(request).execute().printResponse()

            if (response.isSuccessful) {
                response.use {
                    val test = Gson().toJsonTree(response.body)

                    val re = "re"
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        throw IllegalStateException("")
    }
}
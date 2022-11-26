package com.tradiebot.cythero.network.user

import com.google.gson.Gson
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.domain.auth.service.AuthService
import com.tradiebot.cythero.domain.user.model.UserLogin
import com.tradiebot.cythero.network.utils.POST
import com.tradiebot.cythero.network.utils.addFormDataPartIfNotNull
import com.tradiebot.cythero.network.utils.printResponse
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.util.*

class AuthServiceImpl : AuthService {
    private val client = OkHttpClient() // TODO replace with injekt
    private val gson = Gson() // TODO replace with injekt

    override suspend fun loginUser(user: UserLogin): Optional<Auth> {
        val bodyBuilder = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("password", user.password!!)
            .addFormDataPart("from_web", "true")
            .addFormDataPartIfNotNull("email", user.email)
            .addFormDataPartIfNotNull("username", user.username)

        val body = bodyBuilder.build()

        val request: Request = POST(
            url = "https://api.cythero.com/api/user/auth/login",
            body = body
        )

        val response: Response = client.newCall(request).execute().printResponse()
        if (response.isSuccessful) {
            response.use {
                val authUser: Auth = gson.fromJson(response.body.string(), Auth::class.java)
                return Optional.of(authUser)
            }
        }
        return Optional.empty()
    }
}
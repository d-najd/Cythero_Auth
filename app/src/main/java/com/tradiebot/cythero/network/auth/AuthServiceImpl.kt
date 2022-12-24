package com.tradiebot.cythero.network.auth

import com.google.gson.Gson
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.domain.auth.service.AuthService
import com.tradiebot.cythero.domain.user.model.UserLogin
import com.tradiebot.cythero.domain.user.model.UserRegister
import com.tradiebot.cythero.network.utils.*
import okhttp3.OkHttpClient
import okhttp3.Request
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

object AuthServiceImpl : AuthService {
    private val client = Injekt.get<OkHttpClient>()
    private val gson = Injekt.get<Gson>()

    override suspend fun loginUser(user: UserLogin): Auth? {
        val body = MultipartBodyBuilder()
            .addFormDataPart("password", user.password)
            .addFormDataPart("from_web", user.from_web)
            .addFormDataPartIfNotNull("email", user.email)
            .addFormDataPartIfNotNull("username", user.username)
            .addFormDataPartIfNotNull("device_number", user.device_number)
            .addFormDataPartIfNotNull("device_nickname", user.device_nickname)
            .addFormDataPartIfNotNull("pin", user.pin)
            .build()

        val request: Request = POST(
            url = Urls.AUTH_LOGIN,
            body = body
        )
    
        return client.newCall(request).processResponse {
            return@processResponse gson.fromJson(it.body.string(), Auth::class.java)
        }
    }

    override suspend fun registerUser(user: UserRegister): Auth? {
        val body = MultipartBodyBuilder()
            .addFormDataPart("type_id", user.type_id)
            .addFormDataPart("from_web", user.from_web)
            .addFormDataPart("firstName", user.firstName)
            .addFormDataPart("lastName", user.lastName)
            .addFormDataPart("email", user.email)
            .addFormDataPart("username", user.username)
            .addFormDataPart("password", user.password)
            .addFormDataPartIfNotNull("organization_id", user.organization_id)
            .addFormDataPartIfNotNull("pin", user.pin)
            .build()

        val request: Request = POST(
            url = Urls.AUTH_REGISTER,
            body = body
        )
    
        return client.newCall(request).processResponse {
            return@processResponse gson.fromJson(it.body.string(), Auth::class.java)
        }
    }
}
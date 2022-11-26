package com.tradiebot.cythero.network.auth

import com.google.gson.Gson
import com.tradiebot.cythero.domain.auth.model.Auth
import com.tradiebot.cythero.domain.auth.service.AuthService
import com.tradiebot.cythero.domain.user.model.UserLogin
import com.tradiebot.cythero.domain.user.model.UserRegister
import com.tradiebot.cythero.network.utils.POST
import com.tradiebot.cythero.network.utils.Urls
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
            .addFormDataPart("password", user.password)
            .addFormDataPart("from_web", user.from_web.toString())
            .addFormDataPartIfNotNull("email", user.email)
            .addFormDataPartIfNotNull("username", user.username)
            .addFormDataPartIfNotNull("device_number", user.device_number)
            .addFormDataPartIfNotNull("device_nickname", user.device_nickname)
            .addFormDataPartIfNotNull("pin", user.pin)

        val body = bodyBuilder.build()

        val request: Request = POST(
            url = Urls.AUTH_LOGIN,
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

    /**
     * TODO I am assuming that the response is auth object, need to confirm this somehow without spamming
      */
    override suspend fun registerUser(user: UserRegister): Optional<Auth> {
        val bodyBuilder = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("type_id", user.type_id.toString())
            .addFormDataPart("firstName", user.firstName)
            .addFormDataPart("lastName", user.lastName)
            .addFormDataPart("email", user.email)
            .addFormDataPart("username", user.username)
            .addFormDataPart("password", user.password)
            .addFormDataPartIfNotNull("organization_id", user.organization_id)
            .addFormDataPartIfNotNull("pin", user.pin)

        val body = bodyBuilder.build()

        val request: Request = POST(
            url = Urls.AUTH_REGISTER,
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
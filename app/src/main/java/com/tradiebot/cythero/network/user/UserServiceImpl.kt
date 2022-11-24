package com.tradiebot.cythero.network.user

import com.tradiebot.cythero.domain.user.service.UserService
import com.tradiebot.cythero.domain.user.model.UserComplete
import com.tradiebot.cythero.domain.user.model.UserLoginUpdate
import com.tradiebot.cythero.network.user.utils.POST
import com.tradiebot.cythero.network.user.utils.addFormDataPartIfNotNull
import logcat.logcat
import okhttp3.*
import java.io.IOException

class UserServiceImpl : UserService {
    private val client = OkHttpClient() // TODO replace this with injekt

    override suspend fun loginUser(user: UserLoginUpdate): UserComplete? {
        val bodyBuilder = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("password", user.password)
            .addFormDataPart("from_web", "true")
            .addFormDataPartIfNotNull("email", user.email)
            .addFormDataPartIfNotNull("username", user.username)

        val body = bodyBuilder.build()

        val request: Request = POST(
            url = "https://api.cythero.com/api/user/auth/login",
            body = body
        )

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    for ((name, value) in response.headers) {
                        println("$name: $value")
                    }

                    logcat { response.body.string() }
                }
            }
        })
        return null
    }
}
package com.tradiebot.cythero.app

import logcat.logcat
import okhttp3.*
import java.io.IOException


class TestAuth{
    private val client = OkHttpClient()

    fun run() {
        val body: RequestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            // .setType(mediaType!!)
            .addFormDataPart("email", "dimitar.najdovski.example@gmail.com")
            .addFormDataPart("password", "Dimitar123")
            .addFormDataPart("from_web", "true")
            .build()
        val request: Request = Request.Builder()
            .url("https://api.cythero.com/api/user/auth/login")
            .method("POST", body)
            .build()

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

                    logcat{ response.body!!.string() }
                }
            }
        })
    }
}
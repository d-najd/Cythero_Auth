package com.tradiebot.cythero.network.utils

import com.tradiebot.cythero.domain.auth.model.Auth
import okhttp3.Headers
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request

fun MultipartBody.Builder.addFormDataPartIfNotNull(name: String, value: Any?) : MultipartBody.Builder {
    if(value != null){
        addFormDataPart(name, value)
    }
    return this
}

fun MultipartBody.Builder.addFormDataPart(name: String, value: Any) : MultipartBody.Builder = addFormDataPart(name, value.toString())

fun Headers.Builder.addBearerToken(userAuth: Auth): Headers.Builder = addBearerToken(userAuth.token)

fun Headers.Builder.addBearerToken(token: String): Headers.Builder = add("Authorization", "Bearer $token")

@Suppress("FunctionName") // doing it this way so its easier to find
fun MultipartBodyBuilder() = MultipartBody.Builder().setType(MultipartBody.FORM)

@Suppress("FunctionName") // doing it this way so its easier to find
fun HeadersBuilder() = Headers.Builder()

fun OkHttpClient.newCallAndPrint(request: Request) = newCall(request).execute().printResponse()
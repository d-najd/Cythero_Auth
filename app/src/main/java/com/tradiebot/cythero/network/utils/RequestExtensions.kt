package com.tradiebot.cythero.network.utils

import com.tradiebot.cythero.domain.auth.model.Auth
import okhttp3.Headers
import okhttp3.MultipartBody
import okhttp3.internal.http2.Header

fun MultipartBody.Builder.addFormDataPartIfNotNull(name: String, value: Any?) : MultipartBody.Builder {
    if(value != null){
        addFormDataPart(name, value)
    }
    return this
}

fun MultipartBody.Builder.addFormDataPart(name: String, value: Any) : MultipartBody.Builder {
    return addFormDataPart(name, value.toString())
}

fun Headers.Builder.addBearerToken(userAuth: Auth): Headers.Builder {
    return addBearerToken(userAuth.token)
}

fun Headers.Builder.addBearerToken(token: String): Headers.Builder {
    return add("Authorization", "Bearer $token")
}
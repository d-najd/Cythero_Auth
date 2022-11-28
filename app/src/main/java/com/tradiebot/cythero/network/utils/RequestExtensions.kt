package com.tradiebot.cythero.network.utils

import okhttp3.MultipartBody

fun MultipartBody.Builder.addFormDataPartIfNotNull(name: String, value: Any?) : MultipartBody.Builder {
    if(value != null){
        this.addFormDataPart(name, value)
    }
    return this
}

fun MultipartBody.Builder.addFormDataPart(name: String, value: Any) : MultipartBody.Builder {
    this.addFormDataPart(name, value.toString())
    return this
}

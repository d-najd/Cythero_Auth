package com.tradiebot.cythero.network.utils

import okhttp3.MultipartBody

fun MultipartBody.Builder.addFormDataPartIfNotNull(name: String, value: String?) : MultipartBody.Builder {
    if(value != null){
        this.addFormDataPart(name, value)
    }
    return this;
}

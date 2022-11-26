package com.tradiebot.cythero.network.utils

import logcat.LogPriority
import logcat.logcat
import okhttp3.Response

/**
 * If the request was successful prints the request url and method.
 *
 * If the request was unsuccessful prints the request url, method and body.
 *
 * @return the response to allow method chaining
 */
fun Response.printResponse() : Response{
    if(!this.isSuccessful) {
        logcat("Network Error", LogPriority.ERROR)
        { "Got response ${this.networkResponse?.toString()}" +
                " for url ${this.request.url}" +
                " with method ${this.request.method}" +
                " and body ${this.request.body}"}
    } else {
        logcat { "Successful request " +
                " for url ${this.request.url}" +
                " with method ${this.request.method}"
                }
    }
    return this
}


/*
fun MultipartBody.Builder.addFormDataPartIfNotNull(name: String, value: String?) : MultipartBody.Builder {
    if(value != null){
        this.addFormDataPart(name, value)
    }
    return this;
}
 */

package com.tradiebot.cythero.network.utils

import logcat.logcat
import okhttp3.Call
import okhttp3.Response
import java.io.IOException

fun <T> Call.processResponse(
    content: (Response) -> T
): T? {
    try{
        val response = execute().printResponse()
        if(response.isSuccessful && response.code == 200){
            return content(response)
        } else if (response.code == 401){
            logcat { "User Failed to authenticate" }
        } else if (response.code == 409) {
            logcat { "Duplicate Entry" }
        }
        
    } catch (e: Exception){
        when(e){
            is IOException -> logcat { "IOException, likely because there is no internet connection" }
            else -> throw e
        }
    }
    return null
}

/**
 * If the request was successful prints the request url and method.
 *
 * If the request was unsuccessful prints the request url, method and body.
 *
 * @return the response to allow method chaining
 */
fun Response.printResponse() : Response {
    if(!this.isSuccessful) {
        logcat { "Got response ${this.networkResponse?.toString()}" +
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

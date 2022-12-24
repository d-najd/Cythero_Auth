package com.tradiebot.cythero.network.utils

import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.util.view.ContextHolder
import com.tradiebot.cythero.domain.auth.model.Auth
import okhttp3.*
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import java.io.IOException


fun <T> Call.processRequest(
    content: (Response) -> T
): T? {
    try{
        val response = execute().printResponse()
        if(response.isSuccessful && response.code == 200){
            return content(response)
        } else if (response.code == 401){
            Injekt.get<ContextHolder>().composeToast(R.string.error_auth)
        } else if (response.code == 409) {
            Injekt.get<ContextHolder>().composeToast(R.string.error_duplicate_entry)
        }
        
    } catch (e: Exception){
        e.printStackTrace()
        when(e){
            is IOException -> Injekt.get<ContextHolder>().composeToast(R.string.error_network)
            else -> throw e
        }
    }
    return null
}

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
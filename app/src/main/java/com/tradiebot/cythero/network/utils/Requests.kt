@file:Suppress("FunctionName")

package com.tradiebot.cythero.network.utils

import okhttp3.*
import java.util.concurrent.TimeUnit.MINUTES

private val DEFAULT_CACHE_CONTROL = CacheControl.Builder().maxAge(10, MINUTES).build()
private val DEFAULT_HEADERS = Headers.Builder().build()
private val DEFAULT_BODY: RequestBody = FormBody.Builder().build()

fun GET(
    url: String,
    headers: Headers = DEFAULT_HEADERS,
    cache: CacheControl = DEFAULT_CACHE_CONTROL,
): Request = Request.Builder()
        .url(url)
        .headers(headers)
        .cacheControl(cache)
        .build()

fun POST(
    url: String,
    headers: Headers = DEFAULT_HEADERS,
    body: RequestBody = DEFAULT_BODY,
    cache: CacheControl = DEFAULT_CACHE_CONTROL,
): Request = Request.Builder()
        .url(url)
        .post(body)
        .headers(headers)
        .cacheControl(cache)
        .build()

@Suppress("unused")
fun PUT(
    url: String,
    headers: Headers = DEFAULT_HEADERS,
    body: RequestBody = DEFAULT_BODY,
    cache: CacheControl = DEFAULT_CACHE_CONTROL,
): Request = Request.Builder()
        .url(url)
        .put(body)
        .headers(headers)
        .cacheControl(cache)
        .build()

@Suppress("unused")
fun DELETE(
    url: String,
    headers: Headers = DEFAULT_HEADERS,
    body: RequestBody = DEFAULT_BODY,
    cache: CacheControl = DEFAULT_CACHE_CONTROL,
): Request = Request.Builder()
        .url(url)
        .delete(body)
        .headers(headers)
        .cacheControl(cache)
        .build()

package com.tradiebot.cythero.domain.user.model

import logcat.logcat

data class UserLoginApi (
    val email: String? = null,
    val username: String? = null,
    val password: String,
    val device_number: String? = null,
    val device_nickname: String? = null,
    val pin: Int?,
    val from_web: Boolean = true
)

fun UserComplete.toChapterLoginApi(): UserLoginApi {
    val converted = UserLoginApi(
        email = email,
        username = username,
        password = password!!,
        device_number = device_number,
        device_nickname = device_nickname,
        pin = pin,
        from_web = from_web ?: true
    )

    if(converted.username == null && converted.email == null){
        throw IllegalStateException("username and email can't be null at the same time")
    }
    if(!converted.from_web){
        logcat { "from_web is false, this is probably unintended behaviour" }
    }
    return converted
}

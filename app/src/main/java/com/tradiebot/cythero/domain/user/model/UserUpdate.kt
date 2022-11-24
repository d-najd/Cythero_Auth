package com.tradiebot.cythero.domain.user.model

import logcat.logcat

data class UserLoginUpdate (
    val email: String? = null,
    val username: String? = null,
    val password: String,
    val device_number: String? = null,
    val device_nickname: String? = null,
    val pin: Int? = null,
    val from_web: Boolean = true
) {
    init {
        if(username == null && email == null){
            throw IllegalStateException("username and email can't be null at the same time")
        }
        if(!from_web){
            logcat { "from_web is false, this is probably unintended behaviour" }
        }
    }

    companion object {
        fun testingInstance() = UserLoginUpdate(
            email = "dimitar.najdovski.example@gmail.com",
            password = "Dimitar123",
        )
    }
}

fun UserComplete.toChapterLoginUpdate(): UserLoginUpdate {
    return UserLoginUpdate(
        email = email,
        username = username,
        password = password!!,
        device_number = device_number,
        device_nickname = device_nickname,
        pin = pin,
        from_web = from_web ?: true
    )
}
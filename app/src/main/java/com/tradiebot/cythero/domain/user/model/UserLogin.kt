package com.tradiebot.cythero.domain.user.model

import logcat.logcat

/**
 * Class used for logging in
 */
data class UserLogin (
    val email: String? = null,
    val username: String? = null,
    val password: String,
    val device_number: String? = null,
    val device_nickname: String? = null,
    val pin: Int? = null,
    val from_web: Boolean = true,
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
        fun testingInstance() = UserLogin(
            email = "dimitar.najdovski.example@gmail.com",
            password = "Dimitar123",
        )
    }
}

/**
 * Class used for registering user
 */
data class UserRegister(
    val type_id: Long = 1,
    val organization_id: Long? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val username: String,
    val password: String,
    val pin: Int? = null,
    val from_web: Boolean = true,
){
    companion object {
        fun testingInstance() = UserRegister (
            firstName = "Dimitar",
            lastName = "Najdovski",
            username = "DimitarNajdovski",
            email = "dimitar.najdovski.example@gmail.com",
            password = "Dimitar123",
        )
    }
}

@Suppress("unused")
fun User.toUserLogin(): UserLogin {
    return UserLogin(
        email = email,
        username = username,
        password = password!!,
        device_number = device_number,
        device_nickname = device_nickname,
        pin = pin,
        from_web = from_web ?: true,
    )
}

@Suppress("unused")
fun User.toUserRegister(): UserRegister {
    return UserRegister(
        type_id = type_id ?: 1,
        organization_id = organization_id,
        firstName = firstName!!,
        lastName = lastName!!,
        email = email!!,
        username = username!!,
        password = password!!,
        pin = pin,
        from_web = from_web ?: true,
    )
}